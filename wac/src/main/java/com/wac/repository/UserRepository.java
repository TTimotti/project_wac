package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByOrderByUserIdDesc();
    
    // 사용자 로그인 아이디(UserName)가 일치하는 사용자 정보 검색
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUserName(String userName);

}
