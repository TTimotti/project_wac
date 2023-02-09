package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.domain.Order;
import com.wac.domain.OrderLog;
import com.wac.repository.CartRepository;
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
	
	private final CartRepository cartRepository;
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

    /**
     * 영수증 만드는 create
     * @param userName
     * @param storeName
     * @param address
     * @param pickUp
     * @param payment
     * @return
     * @author 추지훈
     */
    public Order create(String userName, String storeName, String address, Integer pickUp, Integer payment, Integer userId) {
        // TODO:
        Order order = Order.builder()
                .pickupService(pickUp)
                .payment(payment)
                .userId(userId)
                .storeName(storeName)
                .address(address)
                .build();
                
       Order entity = orderRepository.save(order);
        
        return entity;
    }

    /**
     * 해당 영수증 상세내열 create
     * @param orderId
     * @param userId
     * @return
     * @author 추지훈
     */
    public OrderLog create(Integer orderId, Integer cartId) {
        // TODO:
        Cart cart = cartRepository.getCartByCartId(cartId); 
        
        OrderLog result = OrderLog.builder()
                
                .build();
                
        
        
        return null;
    }
}
