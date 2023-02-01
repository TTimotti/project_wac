package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wac.domain.Store;
import com.wac.dto.StoreCreateDto;
import com.wac.dto.StoreReadDto;
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
	
}
