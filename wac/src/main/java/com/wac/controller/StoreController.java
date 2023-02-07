package com.wac.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
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
		log.info("storeInfo={}",storeInfo);
		return "/store/store";
	}
	/**
	 *  Axios 비동기 기능을 
	 *  사용하기 위한 컨트롤러
	 * @author 장민석
	 */
	@GetMapping("/storeList")
	public ResponseEntity<List<Store>> readStore (){
		List<Store> storeInfo = storeService.readStoreInfo();
		return ResponseEntity.ok(storeInfo);
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
	@GetMapping("/store/storeDetail")
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
	@PostMapping("/store/storeDetail")
	public String storeUpdate(StoreUpdateDto dto) {
		log.info("storeUpdate(dto = {})",dto);
		Integer storeId = storeService.update(dto);
		log.info("storeId= {}",storeId);
		
		return"redirect:/store";
	}
	/**
	 * 등록한 매장 삭제기능
	 * @param storeId
	 * @author 장민석
	 */
	@PostMapping("/store/storeDelete")
	public String storeDelete(@RequestParam("storeId") Integer storeId) {
		log.info("storeDelete(storeId={})",storeId);
		storeService.delete(storeId);
		return"redirect:/store";
	}
	
	/**
	 * 메뉴 주문페이지로
	 * 상점 선택페이지에서
	 * 선택한 매장이름,주문자 주소를
	 * 넘겨주는 기능.
	 * @param storeName 매장이름
	 * @param userAddress 배달 주소(주문자)
	 * @return 메뉴 오더 페이지로 이동
	 * @author 장민석
	 */
	@GetMapping("/storeInfo")
	public String storeInfo(String storeName, String userAddress) {
		log.info("storeName={}",storeName);
		log.info("userAddress={}",userAddress);
		return "redirect:/order";
	}
	
}
