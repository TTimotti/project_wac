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
		return "/order";
	}
	/**
	 *  Story 페이지
	 * 생성자 : 장민석
	 */
	@GetMapping("/story")
	public String story() {
		log.info("story()");
		return "/story";	
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
	 * 관리자가 프로모션이나 이벤트 작성하는 페이지 
	 * @author: 추지훈
	 */
	@GetMapping("/admin")
    public String newsAdmin() {
        log.info("newsAdmin()");
        return "/admin/admin";
    }

	@GetMapping("/menuDetail")
	public String MenuDetail() {
	    log.info("MenuDetail()");
	    return "/menuDetail";
	}

    @PostMapping("/signInErr") 
    public String signInErr() {
        log.info("signInErr() post 호출");
        
        return "redirect:/user/signIn?error";
    }

}
