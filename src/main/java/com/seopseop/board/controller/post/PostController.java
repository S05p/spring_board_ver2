package com.seopseop.board.controller.post;

import com.seopseop.board.DTO.comment.CommentSaveDTO;
import com.seopseop.board.DTO.post.PostSaveDTO;
import com.seopseop.board.DTO.post.PostUpdateDTO;
import com.seopseop.board.Exception.NotAuthorOfPost;
import com.seopseop.board.Exception.NotAuthoritytoCreatePostException;
import com.seopseop.board.Exception.NotExistPageException;
import com.seopseop.board.Exception.NotExistPostException;
import com.seopseop.board.entity.comment.Comment;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.comment.CommentRepository;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.repository.post.PostRepository;
import com.seopseop.board.service.comment.CommentService;
import com.seopseop.board.service.member.MemberService;
import com.seopseop.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping("/list/{page}")
    public String List (@PathVariable Long page,
                        Model model) {
        // page to int
        int a = page.intValue();
        // pageable
        Page<Post> results = postRepository.findAllActivePost(PageRequest.of(a - 1, 5, Sort.by("id").descending()));
        // exception to Not exist page
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
        return "post/list.html";
    }

    @GetMapping("/create")
    public String GetCreate (Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthoritytoCreatePostException();
        }
        return "post/create.html";
    }

    @PostMapping("/create")
    public String PostCreate (@RequestParam String title,
                              @RequestParam String content,
                              Authentication auth,
                              RedirectAttributes redirectAttributes,
                              @RequestHeader(value = "Referer", required = false) String referer) {
        PostSaveDTO postSaveDTO = new PostSaveDTO(title, content);
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthoritytoCreatePostException();
        }
        Long postId = postService.savePost(postSaveDTO, auth.getName());
        redirectAttributes.addAttribute("successMessage", "글이 작성되었습니다");
        return "redirect:/detail/" + postId;
    }

    @GetMapping("/update/{post_id}")
    public String GetUpdate (@PathVariable Long post_id,
                             Model model,
                             Authentication auth) {
        if (auth == null ||!auth.isAuthenticated()) {
            throw new NotAuthorOfPost();
        }
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

    @GetMapping("/delete/{post_id}")
    public String Delete (Authentication auth,
                          @PathVariable Long post_id,
                          RedirectAttributes redirectAttributes,
                          @RequestHeader(value = "Referer", required = false) String referer) {
        Post post = postService.findById(post_id);
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorOfPost();
        }
        Optional<Member> result = memberRepository.findByUsername(auth.getName());
        Member member = result.orElseThrow(() -> new NotExistPostException());
        if (!post.getPost_writer().equals(member)) {
            throw new NotAuthorOfPost();
        }
        postService.deletePost(post_id,member);
        memberService.decreasePostCnt(member);
        redirectAttributes.addAttribute("redirectMessage","글이 성공적으로 삭제되었습니다.");
        return "redirect:"+referer;
    }

    @GetMapping("/detail/{post_id}")
    public String GetDetail (@PathVariable Long post_id,
                          Model model,
                             Authentication auth) {
        Optional<Post> result = postRepository.findById(post_id);
        Post post = result.orElseThrow(()-> new NotExistPostException());
        model.addAttribute("post",post);
        model.addAttribute("user",auth.getName());
        return "post/detail.html";
    }

    @PostMapping("/detail/{post_id}/{comment_id}")
    public String PostDetailComment (@PathVariable Long post_id,
                              @PathVariable Long comment_id,
                              @RequestParam String content,
                              Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorOfPost();
        }
        Optional<Member> result = memberRepository.findByUsername(auth.getName());
        Member member = result.orElseThrow(() -> new NotExistPostException());
        Post post = postService.findById(post_id);
        Optional<Comment> com = commentRepository.findById(comment_id);
        Comment com2 = com.orElseThrow(()-> new NotExistPostException());
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(content,member,post,com2);
        commentService.saveComment(commentSaveDTO);
        return "redirect:/detail"+post_id;
    }
    @PostMapping("/detail/{post_id}")
    public String PostDetail (@PathVariable Long post_id,
                              @RequestParam String content,
                              Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new NotAuthorOfPost();
        }
        Optional<Member> result = memberRepository.findByUsername(auth.getName());
        Member member = result.orElseThrow(() -> new NotExistPostException());
        Post post = postService.findById(post_id);
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(content,member,post,null);
        commentService.saveComment(commentSaveDTO);
        return "redirect:/detail/"+post_id;
    }


}
