package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.domain.Menu;
import com.wac.service.ImagesService;
import com.wac.service.MenuService;
import com.wac.service.OrderService;

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
    
    @GetMapping("/order/menuOrder")
    public void menuOrder() {
        log.info("menuOrder() 호출");
        
    }
    
    /**
     * 오더페이지로 선택하면 메뉴 정보 넘겨줌. menuId 만 보내서 오더에서 sql 문으로 조회
     * @param menuId
     * @return
     */
    @PostMapping("/order/menuOrder")
    public Menu tossCart(Integer menuId) {
        log.info("tossCart menuId = {}", menuId);
        Menu menu = menuService.readMenu(menuId);
        
//        orderService.create(menuId)
    
        return menu;
    }
    
}

