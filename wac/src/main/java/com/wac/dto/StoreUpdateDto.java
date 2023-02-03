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
public class StoreUpdateDto {
	
	private Integer storeId;
	private String storeName; 
	private String storeAddress;
	private String storePhone;
	private String storeTime;
	private String drinkExplain;
	
	
	public Store toEntity() {
		
		return Store.builder()
				.storeName(storeName)
				.storeAddress(storeAddress)
				.storePhone(storePhone)
				.storeTime(storeTime)
				.drinkExplain(drinkExplain)
				.build();
	}
}
