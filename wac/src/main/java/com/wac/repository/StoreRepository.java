package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wac.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

	/**
	 * @author 장민석
	 */
	List<Store> findByOrderByStoreId();
	Store findBystoreId(Integer storeId);
	List<Store> findByStoreNameIgnoreCaseContainingOrderByStoreIdDesc(String keyword);
	List<Store> findByStoreAddressIgnoreCaseContainingOrderByStoreIdDesc(String keyword);
	List<Store> findByDrinkExplainIgnoreCaseContainingOrderByStoreIdDesc(String keyword);
}
