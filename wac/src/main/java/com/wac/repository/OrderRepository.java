package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByOrderByUserIdDesc();
	
	/**
	 * user ID를 이용해서 order 내용 검색
	 * @param userId
	 * @return
	 */
	Optional<Order> findByUserId(Integer userId);
}