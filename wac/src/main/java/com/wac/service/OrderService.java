package com.wac.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.domain.Order;
import com.wac.domain.OrderLog;
import com.wac.dto.OrderReadDto;
import com.wac.repository.CartRepository;
import com.wac.repository.MenuRepository;
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
	
	private final MenuRepository menuRepository;
	
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
    public Order create(String userName, String storeName, String address, Integer pickUp, Integer payment, Integer userId, Integer totalPrice) {
        Order order = Order.builder()
                .pickupService(pickUp)
                .payment(payment)
                .userId(userId)
                .storeName(storeName)
                .address(address)
                .totalPrice(totalPrice)
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
        
        Cart cart = cartRepository.getCartByCartId(cartId);
        log.info("price ={}", cart);
        log.info("price ={}", cart.getPrice());
        
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
                .quantity(cart.getQuantity())
                .image(cart.getImage())
                .build();
        

        Menu menu1 = menuRepository.getMenuByDotCartId(cart.getMenuId1());
        Menu menu2 = menuRepository.getMenuByDotCartId(cart.getMenuId2());
        Menu menu3 = menuRepository.getMenuByDotCartId(cart.getMenuId3());
        Menu menu4 = menuRepository.getMenuByDotCartId(cart.getMenuId4());
        Menu menu5 = menuRepository.getMenuByDotCartId(cart.getMenuId5());
        Menu menu6 = menuRepository.getMenuByDotCartId(cart.getMenuId6());
        
        log.info("여기까진 성공");
        
        
        if (menu1 != null) {
        result.setPrice1(menu1.getPrice());
        result.setFinalPrice(result.getPrice1() * result.getQuantity()); 
        } 
        
        if (menu2 != null) {
        result.setPrice2(menu2.getPrice());
        result.setFinalPrice(result.getPrice2()  * result.getQuantity());
        }
        
        
        if (menu2 != null && menu3 != null && menu4 != null) {
            result.setPrice3(menu3.getPrice() - 2600);
            result.setPrice4(menu4.getPrice() - 2200);
            
            result.setFinalPrice((menu2.getPrice() + ((menu3.getPrice() - 2600) + (menu4.getPrice() - 2200))) * result.getQuantity());
        } else if (menu3 != null) {
            result.setPrice3(menu3.getPrice());
            result.setFinalPrice(result.getPrice3() * result.getQuantity());
        } else if (menu4 != null) {
            result.setPrice4(menu4.getPrice());
            result.setFinalPrice(result.getPrice4() * result.getQuantity());
        }
    
        
        
        
        
        if (menu5 != null) {
            result.setPrice5(menu5.getPrice());
            result.setFinalPrice(result.getPrice5() * result.getQuantity());
            }
        
        if (menu6 != null) {
            result.setPrice6(menu6.getPrice());
            result.setFinalPrice(result.getPrice6() * result.getQuantity());
            }

        
        
        
        log.info("영수증 만들고 오더로그 저장할떄 저장하기 직전 최종 값 result ={}", result);
        
        OrderLog entity = orderLogRepository.save(result);
        
        return entity;
    }

    @Transactional(readOnly = true)
    public List<OrderReadDto> readOrders(Integer userId) {
        log.info("readOrderDetails");
        
        List<Order> list = orderRepository.readAllList(userId);
        
        return list.stream().map(OrderReadDto::fromEntity).toList();
    }

    public Order readOrder(Integer orderId) {
        Order order = orderRepository.getOrderByOrderId(orderId);
        return order;
    }

    public Integer getUserIdByOrderId(Integer orderId) {
        log.info("오더아이디로 유저 아이디찾는 메서드 호출");
        Integer userId = orderRepository.getUserIdByOrderId(orderId);
        log.info("유저 아이디 = {}", userId);
        return userId;
    }

    public List<OrderLog> getOrderLogListByOrderId(Integer orderId) {
        List<OrderLog> list = orderLogRepository.getListByOrderId(orderId);
        return list;
    }

    public String getStoreNameByOrderId(Integer orderId) {
        String result = orderRepository.getStoreNameByOrderId(orderId);
        return result;
    }

    public String getUserAddressByOrderId(Integer orderId) {
        String result = orderRepository.getUserAddressByOrderId(orderId);
        return result;
    }

    public String getPaymentName(Integer orderId) {
        
        Integer paymentId = orderRepository.getPaymentByOrderId(orderId);
        String paymentName = "관리자 문의";
        switch (paymentId) {
            case 1: paymentName = "현금";
                break;
            case 2: paymentName = "카드";
                break;
            case 3: paymentName = "네이버페이";
                break;
            case 4: paymentName = "카카오페이";
                break;
            case 5: paymentName = "핸드폰 결제";
                break;
        }
        
        return paymentName;
    }

    /**
     * @author 추
     */
    @Transactional(readOnly = true)
    public List<OrderReadDto> readOrderList() {
        List<Order> orders = orderRepository.findByOrderByOrderIdDesc();
        List<OrderReadDto> orderList = new ArrayList<OrderReadDto>();
        for (Order o : orders) {
            orderList.add(OrderReadDto.fromEntity(o));
        }
        
        return orderList;
    }
    
}
