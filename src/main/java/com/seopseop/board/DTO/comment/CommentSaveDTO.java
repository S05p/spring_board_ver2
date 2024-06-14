package com.seopseop.board.DTO.comment;

import com.seopseop.board.entity.comment.Comment;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import lombok.Data;

@Data
public class CommentSaveDTO {

    private String content;
    private Member comment_writer;
    private Post post;
    private Comment parent;

    public CommentSaveDTO(String content, Member comment_writer, Post post, Comment parent) {
        this.content = content;
        this.comment_writer = comment_writer;
        this.post = post;
        this.parent = parent;
    }
}
