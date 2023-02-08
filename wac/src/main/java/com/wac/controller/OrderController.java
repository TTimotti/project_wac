package com.wac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.domain.Order;
import com.wac.dto.CartTossDto;
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
        List<Menu> sideList = cartService.readAllSideMenuByKind();
        List<Menu> drinkList = cartService.readAllDrinkMenuByKind();
        
        model.addAttribute("cartList", cartList);
        model.addAttribute("sideList", sideList);
        model.addAttribute("drinkList", drinkList);
        
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
    
    /**
     * 결제 페이지로 넘겨줘서 결제 상세정보, 결제할때 주소랑 결제수단 선택하는 페이지에 넘겨주기 위한 메서드 
     * @param model
     * @author 추지훈
     */
    @GetMapping("/order/create")
    public void create(Model model) {
        // TODO:: 
    }
    
    /**
     * 그러고 난 뒤 여기서 order 에 해당 유저의 cart 상세내역을 옮겨담고 order_log에 필요한 정보만 남긴뒤 
     * 해당 카트 전체를 삭제한다.  
     * @param data
     * @return
     * @author 추지훈
     */
    @PostMapping("/order/create")
    @ResponseBody
    public ResponseEntity<Order> create(@RequestBody CartTossDto data) {
        log.info("create menuId = {}, userName = {}", data);
        String userName = data.getUserName(); // 주문자명
        Integer userId = 1;
        // TODO::
        
        Order order = orderService.create(userId); 
        
        return ResponseEntity.ok(order);
    }
    
    
}

