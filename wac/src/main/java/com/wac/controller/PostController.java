package com.wac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.dto.PostCreateDto;
import com.wac.dto.PostReadDto;
import com.wac.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class PostController {

    private final PostService postService;
    
    /**
     * post 전체 리스트 kind(종료)별로 불러오는 메서드.
     * @param kind
     * @return
     * @author 추지훈
     */
    @GetMapping("/post/all")
    public ResponseEntity<List<PostReadDto>> readAllPosts(Integer kind) {
        log.info("readAllPosts()");
        List<PostReadDto> list = postService.readPosts(kind);
        log.info("PostReadDto list()", list);
        
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/post/create")
    public void create(Integer id, Model model) {
        log.info("create");
        model.addAttribute("id", id);
    }
    
    /**
     * post 생성메서드 이미지는 보류
     * @param dto, image
     * @return
     * @author 추지훈
     */
    @PostMapping("/post/create")
    public String create(PostCreateDto dto) {
        log.info("create() dto = {}", dto);
        
        postService.create(dto);
        
        return "redirect:/post";
    }
    
    
    
    
}
