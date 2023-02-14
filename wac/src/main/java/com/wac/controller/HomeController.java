package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/") 
    public String home() {
        log.info("home() 호출");
        
        return "/mainPage";   
    }
    
	/**
	 * 메뉴 페이지
	 * 생성자 : 장민석
	 */
	@GetMapping("/menu")
	public String menu() {
		log.info("menu() 호출");
		return "/menu/menu";
	}

	
	/**
	 *   주문 페이지
	 * 생성자 : 장민석
	 */
	@GetMapping("/order")
	public String order() {
		log.info("order()");
		return "/order/order";
	}
	/**
	 *  Story 페이지
	 * 생성자 : 장민석
	 */
	@GetMapping("/story")
	public String story() {
		log.info("story()");
		return "/";	
	}
	
	/**
	 *  What`s News 페이지
	 *  생성자 : 장민석
	 */
	@GetMapping("/news")
	public String whats() {
		log.info("whats()");
		return "/news/news";
	}
	
	/**
	 * 프로모션이나 공지사항을 작성하는 페이지 
	 * @author: 추지훈
	 */
	@GetMapping("/post")
    public String newsAdmin() {
        log.info("newsAdmin()");
        return "/post/post";
    }
	
	/**
     *  
     * @author: 추지훈
     */
    @GetMapping("/order/orderComplete")
    public String orderComplete() {
        log.info("orderComplete()");
        return "/order/orderComplete";
    }
    
    /**
     *  
     * @author: 추지훈
     */
    @GetMapping("/cart")
    public String cartOrder() {
        log.info("cartOrder()");
        return "/cart/cart";
    }

	/**
	 * 로그인 페이지에서 아이디 혹은 비밀번호 오류 발생 시 안내 문구를 띄우기 위해서 이동시키는 페이지
	 * @return
	 * @author 이존규
	 */
    @PostMapping("/signInErr") 
    public String signInErr() {
        log.info("signInErr() post 호출");
        
        return "redirect:/user/signIn?error";
    }
    
    /**
     * 접근 권한이 맞지 않을 때 이동시킬 페이지
     */
	@GetMapping("/denied")
    public void denied() {
        log.info("접근 제한");
    }

}
