package com.seopseop.board.service.comment;

import com.seopseop.board.DTO.comment.CommentSaveDTO;
import com.seopseop.board.entity.comment.Comment;
import com.seopseop.board.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentService {

    Long saveComment(CommentSaveDTO commentSaveDTO, Comment parent);

    Page<Comment> pagingComment(Post post, Pageable pageable);
}
