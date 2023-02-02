package com.wac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.dto.CartCreateDto;
import com.wac.repository.CartRepository;
import com.wac.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Cart Service
 * @author 이존규
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service // 스프링 컨택스트에 service 컴포넌트로 등록.
public class CartService {

    
    private final CartRepository cartRepository;
    
    private final UserRepository userRepository;

    /**
     * userId로 Cart 찾기
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public Cart read(Integer userId) {
    	log.info("read(userId) ={}", userId);
    	
    	return cartRepository.findByUserId(userId).orElse(null);
    }

    public Cart create(Integer menuId, String userName) {
        log.info("create(menu Id = {}, userName = {})", menuId, userName);
        Integer userId = userRepository.findUserIdByUserName(userName);
        
        
        Cart cart = Cart.builder()
                .userId(userId)
                .menuId1(null) // 여기가 버거
                .menuId2(null) // 여기가 세트
                .menuId3(null) // 무조건 감튀저장
                .menuId4(null) // 무조건 콜라저장.
                .quantity(1) // 무조건 수량 1개
                .build();
        
        Cart entity = cartRepository.save(cart);
        
        return entity;
    }
    
    public Cart create(CartCreateDto cartDto) {
        log.info("create(cartDto) ={}", cartDto);
        
        Cart cart = Cart.builder()
                .userId(cartDto.getUserId())
                .menuId1(cartDto.getMenuId1())
                .menuId2(cartDto.getMenuId2())
                .menuId3(cartDto.getMenuId3())
                .menuId4(cartDto.getMenuId4())
                .quantity(cartDto.getQuantity())
                .build();
        
        Cart entity = cartRepository.save(cart);
        
        return entity;
    }

//    public CartCreateDto addDto(Cart cart) {
//        CartCreateDto cartDto =
//        return null;
//    }

    
}