package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {

    @Query("select o from ORDER_LOG o where o.orderId = :orderId order by o.orderLogId desc")
    List<OrderLog> getListByOrderId(@Param("orderId") Integer orderId);

}
