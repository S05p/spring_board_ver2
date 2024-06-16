package com.seopseop.board.service.comment;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;

    QComment qcomment = QComment.comment;

    @Override
    public Long saveComment(CommentSaveDTO commentSaveDTO, Comment parent) {

        Optional<Post> optionalPost = postRepository.findById(commentSaveDTO.getPost().getId());
        Post post = optionalPost.orElseThrow(()-> new NotExistPostException());

        Optional<Member> optionalMember = memberRepository.findByUsername(commentSaveDTO.getComment_writer().getUsername());
        Member member = optionalMember.orElseThrow(()-> new NotAuthorityState());

        if (parent != null) {
            Optional<Comment> optionalComment = commentRepository.findById(parent.getId());
            Comment parentComment = optionalComment.get();
            Comment comment = new Comment(commentSaveDTO.getContent(),commentSaveDTO.getPost(),parentComment,commentSaveDTO.getComment_writer());
            commentRepository.save(comment);

            member.increaseCommentCnt();
            post.increaseCommentCnt();

            return comment.getId();
        } else {
            Comment parentComment = null;
            Comment comment = new Comment(commentSaveDTO.getContent(),commentSaveDTO.getPost(),parentComment,commentSaveDTO.getComment_writer());
            commentRepository.save(comment);

            member.increaseCommentCnt();
            post.increaseCommentCnt();

            return comment.getId();
        }
    }

    @Override
    public Page<Comment> pagingComment(Post post, Pageable pageable) {

        QueryResults<Comment> results = queryFactory.selectFrom(qcomment)
                .where(qcomment.deletedTrue.eq(false),
                        qcomment.post.eq(post))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qcomment.orderNumber.asc(),
                        qcomment.id.asc())
                .fetchResults();

        List<Comment> comments = results.getResults();

        Long total = results.getTotal();

        return new PageImpl<>(comments,pageable,total);
    }
}
