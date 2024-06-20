package com.seopseop.board.entity.member;

import com.seopseop.board.entity.JpaBaseTimeEntity;
import com.seopseop.board.entity.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends JpaBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean deletedTrue;

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private Long postCnt;
    private Long commentCnt;

    @OneToMany(mappedBy = "post_writer")
    private List<Post> written_post;

    @Column(unique = true)
    private String email;

    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = null;
        this.postCnt = 0L;
        this.commentCnt = 0L;
        this.deletedTrue = false;
    }

    public Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.postCnt = 0L;
        this.commentCnt = 0L;
        this.deletedTrue = false;
    }

    public void passwordChange(String password) {
        this.password = password;
    }

    public void resign() {
        this.deletedTrue = true;
    }

    public void increasePostCnt() {
        this.postCnt++;
    }

    public void increaseCommentCnt () {
        this.commentCnt++;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void decreasePostCnt() {
        this.postCnt--;
    }
    public void decreaseCommentCnt() {
        this.commentCnt--;
    }
}
