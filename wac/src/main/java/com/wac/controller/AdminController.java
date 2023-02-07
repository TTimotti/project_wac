package com.wac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Admin 페이지를 위한 controller
 * 
 * @author 김지훈
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public void admin() {
		log.info("admin()");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/userList")
	public void userList() {
		log.info("userList()");
	}

    
}