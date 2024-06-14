package com.seopseop.board.service.comment;

import com.seopseop.board.DTO.comment.CommentSaveDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentService {

    Long saveComment(CommentSaveDTO commentSaveDTO);
}
