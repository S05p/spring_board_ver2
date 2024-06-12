package com.seopseop.board.entity.member;

import com.seopseop.board.entity.JpaBaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
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

    @Column(unique = true)
    private String email;

    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = null;
        this.postCnt = 0L;
        this.commentCnt = 0L;
    }

    public Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.postCnt = 0L;
        this.commentCnt = 0L;
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
