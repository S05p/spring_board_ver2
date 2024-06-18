package com.seopseop.board.DTO.comment;

import com.seopseop.board.entity.comment.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentViewDTO {
    private Long id;
    private String content;
    private Integer depth;
    private Long likes;
    private Long orderNumber;

    public CommentViewDTO(Comment comment){
        this.id = comment.getId();
        this.content = comment.getDeletedTrue() ? "삭제된 댓글입니다" : comment.getContent();
        this.depth = comment.getDepth();
        this.likes = comment.getLikes();
        this.orderNumber = comment.getOrderNumber();
    }
}
