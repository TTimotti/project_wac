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
    
    /**
     * 게시글 생성 카테고리에따라 분류해줘야함 .
     * @param dto
     * @param fid
     * @return
     * @author 추지훈
     */
    public Post create(PostCreateDto dto, Integer fid) {
        log.info("create(dto={})", dto);
        
        Post post = Post.builder()
                .title(dto.getTitle())
                .kind(dto.getKind())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .image(fid)
                .build();
        
        Post entity = postRepository.save(post);
        
        return entity;
    }

    /**
     * 포스트 읽어오는 메서드
     * @param postId
     * @return
     */
	public PostReadDto readPost(Integer postId) {
		log.info("readPost(postId = {})", postId);
		Post post = postRepository.findByPostId(postId);
		PostReadDto postDto = PostReadDto.fromEntity(post);
		
		return postDto;
	}
    /**
     * 게시글 삭제 수정은 필요없음 수정 = 재공지
     * @param id
     * @return
     * @author 추지훈
     */
	public Integer delete(Integer id) {
        log.info("postService delete(id={})", id);
        
        postRepository.deleteById(id);
        
        return id;
    }

}
