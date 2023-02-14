package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByOrderByUserIdDesc();
	
	/**
	 * user ID를 이용해서 order 내용 검색
	 * @param userId
	 * @return
	 */
	Optional<Order> findByUserId(Integer userId);

    @Query("select o from ORDERS o where o.userId = :userId")
    List<Order> readAllList(@Param("userId") Integer userId);

    @Query("select o from ORDERS o where o.orderId = :orderId")
    Order getOrderByOrderId(@Param("orderId") Integer orderId);

    @Query("select o.userId from ORDERS o where o.orderId = :orderId")
    Integer getUserIdByOrderId(Integer orderId);

}
