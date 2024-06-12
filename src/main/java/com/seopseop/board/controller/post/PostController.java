package com.seopseop.board.controller.post;

import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.DTO.post.PostUpdateDTO;
import com.seopseop.board.Exception.NotExistPostException;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.repository.post.PostRepository;
import com.seopseop.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;
    private final MemberRepository memberRepository;

    @GetMapping("/detail/{id}")
    public String Detail (@PathVariable Long id,
                          Model model) {
        Optional<Post> result = postRepository.findById(id);
        model.addAttribute("post",result);

        return "post/detail.html";
    }

    @GetMapping("/create")
    public String GetCreate () {
        return "post/create.html";
    }

    @PostMapping("/create")
    public String PostCreate (@RequestParam String title,
                              @RequestParam String content,
                              Authentication auth) {
        PostSaveDTO postSaveDTO = new PostSaveDTO(title,content);
        Long postId = postService.savePost(postSaveDTO,auth.getName());

        return "redirect:/detail/"+postId;
    }

    @GetMapping("/update/{post_id}")
    public String GetUpdate (@PathVariable Long post_id,
                             Model model) {
        Post post = postService.findById(post_id);
        model.addAttribute("post",post);
        return "post/update.html";
    }

    @PostMapping("/update/{post_id}")
    public String PostUpdate (@PathVariable Long post_id,
                              @RequestParam String title,
                              @RequestParam String content) {
        PostUpdateDTO postUpdateDTO = new PostUpdateDTO(title, content, post_id);
        postService.updatePost(postUpdateDTO);
        return "redirect:/list/1";
    }

    @PostMapping("/delete/{post_id}")
    public String deletePost (Authentication auth,
                              @PathVariable Long post_id,
                              RedirectAttributes redirectAttributes) {
        Post post = postService.findById(post_id);
        Optional<Member> result = memberRepository.findByUsername(auth.getName());
        Member member = result.orElseThrow(() -> new NotExistPostException());



        return "redirect:/detail/"+post_id+"?error=not_post_writer";
    }

}
