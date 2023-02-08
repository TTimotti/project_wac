package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Order;
import com.wac.repository.OrderRepository;
import com.wac.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Order Service
 * @author 이존규
 *
 */
@Slf4j
@Service 
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	private final UserRepository userRepository;
	/**
	 * 모든 order 출력
	 * @return
	 */
    @Transactional(readOnly = true)
    public List<Order> read() {
    	log.info("read all");
    	
    	return orderRepository.findByOrderByUserIdDesc();
    }
	
    @Transactional(readOnly = true)
    public Order read(Integer userId) {
    	log.info("read(userId)");
    	
    	return orderRepository.findByUserId(userId).orElse(null);
    }

    public Order create(String userName, String storeName, String address, Integer pickUp) {
        // TODO:
        Integer userId = userRepository.findUserIdByUserName(userName);
        
        return null;
    }
}
