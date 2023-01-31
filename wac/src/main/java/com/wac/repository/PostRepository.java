package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from POSTS p where p.kind = :kind")
    List<Post> readAllList(@Param("kind") Integer kind);
    Post findByPostId(Integer postId);

}
