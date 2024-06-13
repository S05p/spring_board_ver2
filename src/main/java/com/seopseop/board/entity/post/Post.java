package com.seopseop.board.entity.post;


import com.seopseop.board.entity.JpaBaseTimeEntity;
import com.seopseop.board.entity.comment.Comment;
import com.seopseop.board.entity.member.Member;
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
public class Post extends JpaBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member post_writer;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private Boolean deletedTrue;
    private Long likes;
    private Long commentCnt;
    private Long view;

    private LocalDateTime deletedTime;

    public Post (String title, String content, Member post_writer) {
        this.title = title;
        this.content = content;
        this.post_writer = post_writer;
        this.deletedTrue = false;
        this.likes = 0L;
        this.commentCnt = 0L;
        this.view = 0L;
    }

    public void updatePost (String title, String content, Long id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public void deletePost () {
        this.deletedTrue = true;
        this.deletedTime = LocalDateTime.now();
    }

    public void increaseLike () {
        this.likes++;
    }

    public void decreaseLike () {
        this.likes--;
    }

    public void increaseView () {
        this.view++;
    }

}
