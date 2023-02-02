package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

    List<Users> findByOrderByUserIdDesc();
    
    // 사용자 로그인 아이디(UserName)가 일치하는 사용자 정보 검색
    @EntityGraph(attributePaths = "roles")
    Optional<Users> findByUserName(String userName);

    Optional<Users> findByUserId(Integer userId);

    @Query("select u.userId from USERS u where u.userName = :userName")
    Integer findUserIdByUserName(@Param("userName") String userName);
}
