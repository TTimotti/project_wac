package com.wac.dto;

import com.wac.domain.Post;

import lombok.Builder;
import lombok.Data;

@Builder 
@Data
public class PostReadDto {

    private Integer postId;
    private String title;
    private String content;
    private String author;
    private Integer kind;
    private Integer image;
    
    public static PostReadDto fromEntity(Post entity) {
        return PostReadDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .kind(entity.getKind())
                .image(entity.getImage())
                .build();
    }
}
