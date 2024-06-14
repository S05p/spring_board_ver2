package com.seopseop.board.entity.comment;

import com.seopseop.board.entity.JpaBaseTimeEntity;
import com.seopseop.board.entity.member.Member;
import com.seopseop.board.entity.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends JpaBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member comment_writer;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private Long likes;

    private LocalDateTime deletedTime;
    private Boolean deletedTrue;
    private Integer depth;
    private Long orderNumber;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> replies = new ArrayList();

    public Comment (String content, Post post, Comment parent, Member writer) {
        this.content = content;
        this.post = post;
        this.comment_writer = writer;
        this.parent = parent;
        if (parent == null) {
            this.depth = 0;
            this.orderNumber = post.getCommentCnt();
        } else {
            this.depth = parent.getDepth()+1 ;
            this.orderNumber = parent.getOrderNumber();
        }
        this.deletedTime = null;
        this.deletedTrue = false;
        this.likes = 0L;
    }

    public void updateContent (String content) {
        this.content = content;
    }

    public void deleteComment () {
        this.deletedTrue = true;
        this.deletedTime = LocalDateTime.now();
    }

    public void increaseLike () {
        this.likes++;
    }

    public void decreaseLike () {
        this.likes--;
    }
}
