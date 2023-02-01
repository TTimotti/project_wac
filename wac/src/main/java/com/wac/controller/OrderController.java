package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/order/cart")
    public void cart() {
        log.info("cart() 호출");
        
    }
    
}

