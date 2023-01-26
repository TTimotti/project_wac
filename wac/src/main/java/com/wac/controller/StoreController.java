package com.wac.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wac.domain.Store;
import com.wac.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Store 페이지 컨트롤러
 * @author 장민석
 */

@Controller
@Slf4j
@RequiredArgsConstructor
public class StoreController {
	
	private final StoreService storeService;
	
	/**
	 *  Store 페이지
	 * @author 장민석
	 */
	@GetMapping("/store")
	public String store(Model model) {
		
		log.info("store()");
		List<Store> storeInfo = storeService.readStoreInfo();
		model.addAttribute("storeInfo",storeInfo);
		
		return "/store/store";
	}
	
	
}
