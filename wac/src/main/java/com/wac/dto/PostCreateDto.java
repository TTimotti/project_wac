package com.wac.dto;

import com.wac.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostCreateDto {

    private String title;
    private Integer kind;
    private String content;
    private String author;
    private Integer image;
    
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .kind(kind)
                .content(content)
                .author(author)
                .image(image)
                .build();
    }
}
