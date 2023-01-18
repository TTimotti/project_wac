package com.wac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
		return "/menu";
	}
	
	/**
	 *  Store 페이지
	 * 생성자 : 장민석
	 */
	@GetMapping("/store")
	public String store() {
		log.info("store()");
		return "/store/store";
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
	@GetMapping("/whatNews")
	public String whats() {
		log.info("whats()");
		return "/whats";
	}
}
