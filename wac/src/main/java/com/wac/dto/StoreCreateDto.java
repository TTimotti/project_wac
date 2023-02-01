package com.wac.dto;

import com.wac.domain.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

/**
 * 매장 등록 Dto
 * @author 장민석
 *
 */
public class StoreCreateDto {
	
	private String storeName; 
	private String storeAddress;
	private String storeAddress2;
	private String storePhone;
	private String storeTime;
	private String drinkExplain;
	
	
	public Store toEntity() {
		
		return Store.builder()
				.storeName(storeName)
				.storeAddress(storeAddress + " " + storeAddress2)
				.storePhone(storePhone)
				.storeTime(storeTime)
				.drinkExplain(drinkExplain)
				.build();
	}
}
