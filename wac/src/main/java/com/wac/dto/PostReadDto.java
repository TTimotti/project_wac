package com.wac.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String createdTime;
    
    public static PostReadDto fromEntity(Post entity) {
        return PostReadDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .kind(entity.getKind())
                .image(entity.getImage())
                .createdTime(entity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
