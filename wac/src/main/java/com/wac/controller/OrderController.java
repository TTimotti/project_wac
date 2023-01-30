package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/order/menuOrder")
    public void menuOrder() {
        log.info("menuOrder() 호출");
        
    }
}
