package com.seopseop.board.controller.post;

import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/list")
public class PostController {
    private final PostRepository postRepository;

    @GetMapping("/{id}")
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



        return "redirect:/detail";

    }
}
