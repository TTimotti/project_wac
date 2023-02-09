package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.domain.Order;
import com.wac.domain.OrderLog;
import com.wac.repository.CartRepository;
import com.wac.repository.OrderLogRepository;
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
	
	private final OrderLogRepository orderLogRepository;
	
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
    public OrderLog create(Integer orderId, Integer cartId, Integer userId, String userName) {
        // TODO:
        Cart cart = cartRepository.getCartByCartId(cartId); 
        // 해당 카트 내용 다 가져와서 및에 복붙처럼 저장.
        OrderLog result = OrderLog.builder()
                .orderId(orderId)
                .cartId(cartId)
                .userId(userId)
                .userName(userName)
                .menuId1(cart.getMenuId1())
                .menuId2(cart.getMenuId2())
                .menuId3(cart.getMenuId3())
                .menuId4(cart.getMenuId4())
                .menuId5(cart.getMenuId5())
                .menuId6(cart.getMenuId6())
                .image(cart.getImage())
                .build();
                
        OrderLog entity = orderLogRepository.save(result);
        
        return entity;
    }
}
