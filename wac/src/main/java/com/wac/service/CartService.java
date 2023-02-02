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

    /** 
     * 카트 생성 및 초기데이터
     * @param menuId
     * @param userName
     * @return
     * @author 추지훈
     */
    public Cart create(Integer menuId, String userName) {
        log.info("create(menu Id = {}, userName = {})", menuId, userName);
        Integer userId = userRepository.findUserIdByUserName(userName);
        
        
        Cart cart = Cart.builder()
                .userId(userId)
                
                .quantity(1) // 무조건 수량 1개
                .build();
        
//        CartCreateDto cart = null;
        Cart entity = cartRepository.save(cart);
        
        return entity;
    }
    
    /**
     * 카트에 메뉴 담기
     * @param cartDto
     * @return
     * @author 추지훈
     */
    public Cart create(Cart cartDto) {
        log.info("create(cartDto) ={}", cartDto);
        
        Cart cart = Cart.builder()
                .userId(cartDto.getUserId())
                .menuId1(cartDto.getMenuId1())
                .menuId2(cartDto.getMenuId2())
                .menuId3(cartDto.getMenuId3())
                .menuId4(cartDto.getMenuId4())
                .menuId5(cartDto.getMenuId5())
                .menuId6(cartDto.getMenuId6())
                .quantity(cartDto.getQuantity())
                .build();
        
        Cart entity = cartRepository.save(cart);
        
        return entity;
    }

    public Integer delete(Integer cartId) {
        cartRepository.deleteById(cartId);
        return cartId;
    }


    
}