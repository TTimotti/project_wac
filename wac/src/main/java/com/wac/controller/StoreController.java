package com.wac.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wac.domain.Store;
import com.wac.dto.StoreCreateDto;
import com.wac.dto.StoreReadDto;
import com.wac.dto.StoreUpdateDto;
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
	 * Store-Select 페이지
	 * @author 장민석
	 */
	@GetMapping("/store-select")
	public String select(Model model) {
		log.info("store-select()");
		List<Store> storeInfo = storeService.readStoreInfo();
		model.addAttribute("storeInfo",storeInfo);
		return "/store/store-select";
	}
	
	/**
	 * store Create 페이지
	 */
	@GetMapping("/storeCreate")
	public String storeCreate() {
		log.info("storeCreate()");
		return "/store/storeCreate";
	}

	/**
	 * Store Create 생성
	 * @param dto
	 * @return
	 * @author 장민석
	 */
	@PostMapping("/storeCreate")
	public String createStore(StoreCreateDto dto) {
		log.info("store-create()");
		storeService.storeCreate(dto);
		return "redirect:/store";
	}
	
	/**
	 * @param storeId
	 * @param model
	 * @author 장민석
	 */
	@GetMapping("store/storeDetail")
	public void storeDetail (@RequestParam("storeId") Integer storeId, Model model) {
		log.info("storeDetail()",storeId);
		StoreReadDto storeDto = storeService.readStore(storeId);
		model.addAttribute("store", storeDto);
	}
	
	/**
	 * @param dto
	 * @return 수정한 매장정보 페이지
	 * @author 장민석
	 */
	@PostMapping("store/storeDetail")
	public String storeUpdate(StoreUpdateDto dto) {
		log.info("storeUpdate(dto = {})",dto);
		Integer storeId = storeService.update(dto);
		log.info("storeId= {}",storeId);
		
		return"redirect:/store";
	}
	/**
	 * 
	 */
	@PostMapping("/store/storeDelete")
	public String storeDelete(@RequestParam("storeId") Integer storeId) {
		log.info("storeDelete(storeId={})",storeId);
		storeService.delete(storeId);
		return"redirect:/store";
	}
}
