package com.wac.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.domain.Cart;
import com.wac.service.CartService;
import com.wac.service.ImagesService;
import com.wac.service.MenuService;
import com.wac.service.OrderService;
import com.wac.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 주문하기위한 Controller
 * 
 * @author 서범수, 추지훈
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping
public class OrderController {
    
    private final MenuService menuService;
    
    private final ImagesService imagesService;
    
    private final OrderService orderService;
    
    private final UserService userService;
    
    private final CartService cartService;
    
    @GetMapping("/order/cart")
    public void gotoCart(String userName, Model model) {
        log.info("gotoCart(userName={})", userName);
        
        List<Cart> cartList = cartService.readAllByUserName(userName);
        
        
        model.addAttribute("cartList", cartList);
        
    }
    
    /**
     * 매장 선택 값을 오더 메뉴선택 페이지로 넘겨주는 기능
     * @author 장민석
     * @param storeName 매장이름
     * @param userAddress 매장에서 배달가야될 유저 주소
     * @param model 메뉴오더 페이지로 넘어온값을 model객체에 담아 전달해줌.
     * @return
     */
    @PostMapping("/order")
    public String jump(String storeName, String userAddress, Model model) {
		log.info("storeName={}",storeName);
		log.info("userAddress={}",userAddress);

		model.addAttribute("storeName",storeName);
		model.addAttribute("userAddress",userAddress);
    	return "/order/order";
    }
    
}

