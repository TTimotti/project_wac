package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.dto.MenuSimpleDto;
import com.wac.repository.CartRepository;
import com.wac.repository.MenuRepository;
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
    
    private final MenuRepository menuRepository;

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
                .userName(cartDto.getUserName())
                .menuId1(cartDto.getMenuId1())
                .menuId2(cartDto.getMenuId2())
                .menuId3(cartDto.getMenuId3())
                .menuId4(cartDto.getMenuId4())
                .menuId5(cartDto.getMenuId5())
                .menuId6(cartDto.getMenuId6())
                .address(cartDto.getAddress())
                .storeName(cartDto.getStoreName())
                .image(cartDto.getImage())
                .quantity(cartDto.getQuantity())
                .build();
        log.info("create(err1) cart = {}", cart);
        Cart entity = cartRepository.save(cart);
        log.info("create(err2) entity = {}", entity);
        
        return entity;
    }

    /**
     * 카트 삭제
     * @param cartId
     * @return
     * @author 추지훈 
     */
    public Integer delete(Integer cartId) {
        log.info("delete 서비스 호출 cartId = {}", cartId);
        cartRepository.deleteById(cartId);
        return cartId;
    }

    public List<Cart> readAllByUserName(String userName) {
            
            log.info("readAllByUserName(userName={})", userName);
            
            Integer userId = userRepository.findUserIdByUserName(userName);
            
            List<Cart> list = cartRepository.findByUserIdOrderByCartId(userId);
            
            return list;
    
        }
    
    /**
     * 카트에서 수량을 하나씩 줄이는 메서드.
     * @param cartListId
     * @return 줄인 값(=1)
     * @author 서범수
     */
    @Transactional
    public Integer lessQ(String cartListId) {
        log.info("lessQ(cart={})", cartListId);
        Integer cartId = Integer.parseInt(cartListId.substring(0, cartListId.length() - 1));
        Integer lessQ = cartRepository.lessQBycartId(cartId);
        return lessQ;
    }
    
    /**
     * 카트에서 수량을 하나씩 늘리는 메서드.
     * @param cartListId
     * @return 줄인 값(=1)
     * @author 서범수
     */
    @Transactional
    public Integer moreQ(String cartListId) {
        log.info("moreQ(cart={})", cartListId);
        Integer cartId = Integer.parseInt(cartListId.substring(0, cartListId.length() - 1));
        Integer moreQ = cartRepository.moreQBycartId(cartId);
        return moreQ;
    }
    
    /**
     * 사이드메뉴 정보 가져오는 메서드
     * @return List<Menu> 사이드메뉴 리스트
     * @author 서범수
     */
    public List<Menu> readAllSideMenuByKind() {
            log.info("readAllSideMenuByKind() 호출");
            List<Menu> sideList = menuRepository.findAllSideMenuByKindOrderBymenuId();
        return sideList;
    }
    
    /**
     * 음료메뉴 정보 가져오는 메서드
     * @return List<Menu> 음료메뉴 리스트
     * @author 서범수
     */
    public List<Menu> readAllDrinkMenuByKind() {
            log.info("readAllDrinkMenuByKind() 호출");
            List<Menu> sideList = menuRepository.findAllDrinkMenuByKindOrderBymenuId();
        return sideList;
    }
    
    /**
     * 카트에서 사이드 메뉴를 변경한느 메서드.
     * @param cartId
     * @param menuId
     * @author 서범수
     */
    @Transactional
    public void changeSideMenu(Integer cartId, Integer menuId) {
        log.info("chageSideMenu(cartId={}, menuId={})", cartId, menuId);
        
        cartRepository.changeSideMenu(cartId, menuId);
        
    }
    
    /**
     * 카트에서 음료수 메뉴를 변경한느 메서드.
     * @param cartId
     * @param menuId
     * @author 서범수
     */
    @Transactional
    public void changeDrinkMenu(Integer cartId, Integer menuId) {
        log.info("chageDrinkMenu(cartId={}, menuId={})", cartId, menuId);
        
        cartRepository.changeDrinkMenu(cartId, menuId);
        
    }
    
    /**
     * 카트에서 메뉴 지우는 메서드
     * @param menuId, cartId
     * @author 서범수.
     */
    public void deleteCartItem(Integer cartId) {
        log.info("deleteCartItem(cartId={})", cartId);
        
        cartRepository.deleteBycartId(cartId);
        
    }

    /**
     * 해당 유저의 장바구니리스트를 불러옴.
     * @param userId
     * @return
     * @author 추지훈
     */
    public List<Cart> readCartList(Integer userId) {
        List<Cart> list = cartRepository.findByUserIdOrderByCartId(userId);
        
        return list;
    }
    
    /**
     * 사이드메뉴 정보 간략하게 가져오는 메서드
     * @return List<MenuSimpleDto>
     * @author 서범수
     */
    public List<MenuSimpleDto> readSimpleMenuByKind(Integer kind) {
        log.info("readSimpleSideMenuByKind() 호출");
        List<MenuSimpleDto> sideList = menuRepository.findSimpleMenuByKindOrderByMenudId(kind);

        return sideList;
    }


    
}