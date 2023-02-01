package com.wac.dto;

import com.wac.domain.Store;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreReadDto {

	private Integer storeId;
	private String storeName; 
	private String storeAddress;
	private String storePhone;
	private String storeTime;
	private String drinkExplain;
	
	public static StoreReadDto fromEntity(Store entity) {
		
		return StoreReadDto.builder()
				.storeName(entity.getStoreName())
				.storeAddress(entity.getStoreAddress())
				.storePhone(entity.getStorePhone())
				.storeTime(entity.getStoreTime())
				.drinkExplain(entity.getDrinkExplain())
				.build();
	}
}
