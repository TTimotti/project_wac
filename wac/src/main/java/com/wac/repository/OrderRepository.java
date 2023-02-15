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
    Integer getUserIdByOrderId(@Param("orderId") Integer orderId);

    @Query("select o.storeName from ORDERS o where o.orderId = :orderId")
    String getStoreNameByOrderId(@Param("orderId") Integer orderId);

    @Query("select o.address from ORDERS o where o.orderId = :orderId")
    String getUserAddressByOrderId(@Param("orderId") Integer orderId);
    
    @Query("select o.payment from ORDERS o where o.orderId = :orderId")
    Integer getPaymentByOrderId(@Param("orderId") Integer orderId);

    List<Order> findByOrderByOrderIdDesc();

}
