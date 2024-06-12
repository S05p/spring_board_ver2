package com.seopseop.board.DTO.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostSaveDTO {
    private String title;
    private String content;

    public PostSaveDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
