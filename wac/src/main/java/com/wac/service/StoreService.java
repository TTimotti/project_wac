package com.wac.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.wac.domain.Store;
import com.wac.dto.StoreCreateDto;
import com.wac.dto.StoreReadDto;
import com.wac.dto.StoreUpdateDto;
import com.wac.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Store Service
 * @author 장민석
 *
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {
	
	private final StoreRepository storeRepository;
	
	/**
	 * @author 장민석
	 * 매장 정보 Read
	 */
	public List<Store> readStoreInfo() {
		log.info("readStoreInfo");
		return storeRepository.findByOrderByStoreId();
	}
	/**
	 * 매장 생성
	 * @author 장민석
	 * @param dto
	 * @return entity
	 */
	public Store storeCreate(StoreCreateDto dto) {
		log.info("storeCreate()");
		Store entity = storeRepository.save(dto.toEntity());
		return entity;
	}
	public StoreReadDto readStore(Integer storeId) {
		log.info("StoreReadById()",storeId);
		Store store = storeRepository.findBystoreId(storeId);
		StoreReadDto storeDto = StoreReadDto.fromEntity(store);
		return storeDto;
	}
	
	@Transactional
	public Integer update(StoreUpdateDto dto) {
		log.info("update(dto={})",dto);
		
		Store entity = storeRepository.findById(dto.getStoreId()).get();
		entity.update(dto.getStoreName(),dto.getStoreAddress(),
				dto.getStorePhone(),dto.getStoreTime(),dto.getDrinkExplain());
		return entity.getStoreId();
	}
	/**
	 * 매장 등록 삭제 기능
	 * @param storeId
	 * @return
	 * @author 장민석
	 */
	public Integer delete(Integer storeId) {
		log.info("delete(id={})",storeId);
		storeRepository.deleteById(storeId);
		return storeId;
	}
	
	/**
	 * 검색어의 타입과 키워드를 받아 매장 검색기능을 구현.
	 * @param type 검색어 분류
	 * @param keyword 검색어 
	 * @return 검색한 해당되는 리스트
	 */
	public List<Store> search(String type, String keyword) {
		log.info("search(type={},keyword={})", type, keyword );
		
		List<Store> list = new ArrayList<>();
		
		switch (type) {
		case "n": // 매장 이름 검색
			list = storeRepository.findByStoreNameIgnoreCaseContainingOrderByStoreIdDesc(keyword);
			break;
		case "a": // 매장 주소로 검색
			list = storeRepository.findByStoreAddressIgnoreCaseContainingOrderByStoreIdDesc(keyword);
			break;
		case "d": // 매장 서비스로 검색
			list = storeRepository.findByDrinkExplainIgnoreCaseContainingOrderByStoreIdDesc(keyword);
		}
		return list;
	}
	
}
