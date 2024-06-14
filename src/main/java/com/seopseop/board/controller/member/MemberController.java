package com.seopseop.board.controller.member;


import com.seopseop.board.Exception.NotAuthorityState;
import com.seopseop.board.Exception.NotExistPageException;
import com.seopseop.board.Exception.Notequalpassword;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.service.member.MemberService;
import com.seopseop.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/login")
    public String Login () {
        return "member/login.html";
    }

    @GetMapping("/signup")
    public String GetSignup () {
        return "member/signup.html";
    }

    @PostMapping("/signup")
    public String PostSignup (@RequestParam String username,
                              @RequestParam String password1,
                              @RequestParam String password2,
                              @RequestParam String nickname) {

        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isPresent()) {
            return "redirect:/user/signup?error=username_exist";
        }
        if (!password1.equals(password2) || password1.length() <= 7) {
            return "redirect:/user/signup?error=password_problem";
        }
        var hashed = new BCryptPasswordEncoder().encode(password1);

        memberRepository.save(new Member(username,hashed,nickname));

        return "redirect:/list/1";
    }

    @GetMapping("/update")
    public String GetUpdate (Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }
        return "member/update.html";
    }

    @PostMapping("/update")
    public String PostUpdate (Authentication auth,
                              @RequestParam String nickname) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }

        Member member= memberService.findActiveMemberByUsername(auth.getName());
        memberService.updateMember(member,nickname);
        return "rediect:/list/1";
    }

    @GetMapping("/passwordchange")
    public String GetPasswordChange (Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }
        return "member/passwordchange.html";
    }


    @PostMapping("/passwordchange")
    public String PostPasswordChange (Authentication auth,
                                      @RequestParam String password1,
                                      @RequestParam String password2){
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }
        Member member= memberService.findActiveMemberByUsername(auth.getName());
        if (!password1.equals(password2)) {
            throw new Notequalpassword();
        }
        String hashed = new BCryptPasswordEncoder().encode(password1);
        memberService.passwordChange(member,hashed);
        return "redirect:/list/1";
    }
    @GetMapping("/resign")
    public String resign (Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }
        Member member= memberService.findActiveMemberByUsername(auth.getName());
        memberService.resign(member);
        return "redirect:/list/1";
    }

    @GetMapping("/mypage/post/{page}")
    public String myPage(Authentication auth,
                         Model model,
                         Pageable pageable,
                         @PathVariable Long page) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorityState();
        }
        Member member = memberService.findActiveMemberByUsername(auth.getName());
        // page to int
        int a = page.intValue();
        // pageable
        Page<Post> results = postService.findAllActivePostByMember(member.getUsername(),PageRequest.of(a-1,5,Sort.by("id").descending()));
        if (results.isEmpty()) {
        throw new NotExistPageException();
        }
        // pagination button
        int totalPages = results.getTotalPages();
        int currentPage = a;
        int startPage = Math.max(1, currentPage - 5);
        int endPage = Math.min(totalPages, currentPage + 4);
        if (endPage - startPage < 9) {
            if (startPage == 1) {
                endPage = Math.min(startPage + 9, totalPages);
                            } else if (endPage == totalPages) {
                startPage = Math.max(1, endPage - 9);
                            }
        }
        model.addAttribute("results", results.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasNext", results.hasNext());
        model.addAttribute("hasPrevious", results.hasPrevious());
        model.addAttribute("user",member);
        return "member/mypage.html";

    }
}