package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wac.domain.Cart;
import com.wac.domain.Order;
import com.wac.domain.Users;
import com.wac.service.CartService;
import com.wac.service.OrderService;
import com.wac.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Cart를 관리하기 위한 controller
 * 
 * @author 이존규
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

	private final UserService userService;
	private final OrderService orderService;
	private final CartService cartService;
	
	@GetMapping("cart")
	public void cartPage(Model model, String userName, Integer userId) {
		log.info("cart page = userId{}, userName{]", userId, userName);
		  Users user = null;
		  Cart cart = null;
		  Order order = null;
		  
	        if(userName == null) {
	            user = userService.read(userId);
	        } else {
	            user = userService.read(userName);
	        }
	    
	        cart = cartService.read(user.getUserId());
	        order = orderService.read(user.getUserId());
	        
	        if(cart == null) {
	        	
	        } else {
		        model.addAttribute("cart", cart);
	        }
	        
	        if(order == null) {

	        }else {
		        model.addAttribute("order",order);
	        }
	        
	        model.addAttribute("user", user);
	        
	}
	
	
}