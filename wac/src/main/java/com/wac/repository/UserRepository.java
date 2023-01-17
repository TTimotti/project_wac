package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByOrderByUserIdDesc();

}
