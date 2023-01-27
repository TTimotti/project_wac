package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Post;
import com.wac.dto.PostCreateDto;
import com.wac.dto.PostReadDto;
import com.wac.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostReadDto> readPosts(Integer kind) {
        log.info("readMenus");
        
        List<Post> list = postRepository.readAllList(kind);
        
        return list.stream().map(PostReadDto::fromEntity).toList();
    }
    
    public Post create(PostCreateDto dto) {
        log.info("create(dto={}", dto);
        
        Post post = Post.builder() // TODO: 나중에 image 넣어줘야함.
                .title(dto.getTitle())
                .kind(dto.getKind())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();
        
        Post entity = postRepository.save(post);
        
        return entity;
    }

}
