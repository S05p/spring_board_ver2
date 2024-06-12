package com.seopseop.board.DTO.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateDTO {

    private String title;
    private String content;
    private Long id;

    public PostUpdateDTO(String title, String content, Long id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }
}
