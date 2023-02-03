package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	
	/**
	 * user Id를 이용해서 Cart 내역 검색
	 * @param userId
	 * @return
	 */
	List<Cart> findByUserId(Integer userId);
	
}
