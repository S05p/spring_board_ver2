package com.seopseop.board.DTO.member;

import lombok.Data;

@Data
public class MemberSaveDTO {

    private String username;

    private String password;

    private String nickname;

    public MemberSaveDTO(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
