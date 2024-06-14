package com.seopseop.board.service.comment;

import com.seopseop.board.DTO.comment.CommentSaveDTO;
import com.seopseop.board.Exception.NotAuthorityState;
import com.seopseop.board.Exception.NotExistPageException;
import com.seopseop.board.Exception.NotExistPostException;
import com.seopseop.board.entity.comment.Comment;
import com.seopseop.board.entity.comment.QComment;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import com.seopseop.board.repository.comment.CommentRepository;
import com.seopseop.board.repository.member.MemberRepository;
import com.seopseop.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    QComment qcomment = QComment.comment;

    @Override
    public Long saveComment(CommentSaveDTO commentSaveDTO) {

        Optional<Post> optionalPost = postRepository.findById(commentSaveDTO.getPost().getId());
        Post post = optionalPost.orElseThrow(()-> new NotExistPostException());

        Optional<Member> optionalMember = memberRepository.findByUsername(commentSaveDTO.getComment_writer().getUsername());
        Member member = optionalMember.orElseThrow(()-> new NotAuthorityState());

        Comment parentComment = null;

        if (commentSaveDTO.getParent() != null) {
            Optional<Comment> optionalComment = commentRepository.findById(commentSaveDTO.getParent().getId());
            parentComment = optionalComment.orElseThrow(()-> new NotExistPageException());
        }

        Comment comment = new Comment(commentSaveDTO.getContent(),commentSaveDTO.getPost(),commentSaveDTO.getParent(),commentSaveDTO.getComment_writer());
        commentRepository.save(comment);

        member.increaseCommentCnt();

        return comment.getId();
    }
}
