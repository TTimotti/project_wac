package com.wac.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.domain.Menu;
import com.wac.dto.OrderCreateDto;
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
    
    private final UserService userSerivce;
    
//    @GetMapping("/order/menuOrder")
//    public void menuOrder() {
//        log.info("menuOrder() 호출");
//        
//    }
    
    /**
     * 오더페이지로 선택하면 메뉴 정보 넘겨줌. menuId 만 보내서 오더에서 sql 문으로 조회
     * 메뉴를 선택하는 동시에 유저 정보와 메뉴정보가 같이 cart 테이블에 저장되게 해야함
     * 이 메서드안에서 메뉴조회 유저조회 cart 테이블 create 기능이 동시에 이러나야함.
     * @param menuId
     * @return
     * @author 추지훈
     */
    @PostMapping("/order/menuOrder")
    public String menuOrder(Integer menuId, String loginUser) {
        log.info("tossCart menuId = {}", menuId);
        
        Integer userId = userSerivce.getUserIdByUserName(loginUser);
        
        log.info("유저 아이디 = {}", userId);
        
        Menu menu = menuService.readMenu(menuId);
        // TODO
        OrderCreateDto dto;
//        dto.setMenuId(menuId);
//        dto.setUserId(userId);
//        orderService.create(dto);
        
        
//        orderService.create(menuId)
    
        return "redirect:/order/menuOrder";
    }
    
}

