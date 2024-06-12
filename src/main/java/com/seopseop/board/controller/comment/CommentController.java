package com.seopseop.board.controller.comment;

import com.seopseop.board.entity.member.Member;
import com.seopseop.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberRepository memberRepository;



}
