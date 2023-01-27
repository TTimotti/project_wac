package com.wac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
	/**
	 * Select 페이지
	 * @author 장민석
	 */
	@GetMapping("/store-select")
	public String select(Model model) {
		log.info("store-select");
		List<Store> storeInfo = storeService.readStoreInfo();
		model.addAttribute("storeInfo",storeInfo);
		return "/store/store-select";
	}
	
	/**
	 * 스토어 목록에서 스토어 선택시 선택한 매장명을
	 * 리스트에서 추출.
	 * @author 장민석
	 * @param Store
	 * @return
	 */
	public ResponseEntity<List<Store>> select(String Store) {
		
		
		
		return ResponseEntity.ok(null);
	}
	
}
