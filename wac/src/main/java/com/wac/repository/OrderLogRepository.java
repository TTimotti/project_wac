package com.wac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, Integer> {

}
