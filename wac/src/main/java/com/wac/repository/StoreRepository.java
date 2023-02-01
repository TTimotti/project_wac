package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

	/**
	 * @author 장민석
	 */
	List<Store> findByOrderByStoreId();

	Store findBystoreId(Integer storeId);
	
}
