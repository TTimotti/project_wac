package com.wac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Cart;

public interface OrderRepository extends JpaRepository<Cart, Integer> {

}
