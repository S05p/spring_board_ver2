package com.seopseop.board.controller.member;


import com.seopseop.board.entity.member.Member;
import com.seopseop.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

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
}
