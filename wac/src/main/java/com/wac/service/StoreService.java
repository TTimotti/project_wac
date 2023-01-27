package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wac.domain.Store;
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
	
	
	public List<Store> readStoreInfo() {
		
		log.info("readStoreInfo");
		
		return storeRepository.findByOrderByStoreId();
	}
}
