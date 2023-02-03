package com.wac.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wac.dto.PostCreateDto;
import com.wac.dto.PostReadDto;
import com.wac.service.ImagesService;
import com.wac.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class PostController {

    private final PostService postService;
    private final ImagesService imagesService;
    
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
    
    @PreAuthorize("hasRole('ADMIN')")
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
    public String create(PostCreateDto dto, @RequestParam("image") MultipartFile file) throws IllegalStateException, IOException {
        log.info("create() dto = {}, file = {}", dto, file);
        
        Integer fid = imagesService.saveImage(file);
        postService.create(dto, fid);
        
        
        return "redirect:/post";
    }
    
    /**
     * Post 객체를 DB에서 받아와 Model에 담아 View로 전달.
     * @param kind
     * @return
     * @author 추지훈
     */
    @GetMapping("/post/detail")
    public void detail(@RequestParam("postId") Integer postId, Model model) {
        log.info("post/detail(postId = {})", postId);
        PostReadDto postDto = postService.readPost(postId);
        model.addAttribute("post", postDto);        
    }
    
//    /**
//     * 종속관계인 댓글이달린 포스트 삭제
//     * 
//     * @param id
//     * @param attrs
//     * @return 추
//     */
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    @PostMapping("/post/delete")
//    public String delete(Integer id, RedirectAttributes attrs) {
//        
//        log.info("PostController delete(id={})", id);
//        
//        Integer postId = postService.delete(id);
//        attrs.addFlashAttribute("deletedPostId", postId);
//        log.info("postController delete postId = {}", postId);
//        
//        return "redirect:/post";
//    }
    
    
    
    
}
