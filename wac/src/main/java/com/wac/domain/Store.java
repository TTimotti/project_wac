package com.wac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 매장 테이블
 * @author 추지훈
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "STORES")
@SequenceGenerator(name = "STORES_SEQ_GEN", sequenceName = "STORES_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORES_SEQ_GEN")
    @Column(name = "store_id")
    private Integer storeId;
    
    @Column(name = "store_name")
    private String storeName; 
    // 이름에 DT가 들어가면 DT점으로 구분 
    
    @Column(name = "store_address")
    private String storeAddress;
    
    @Column(name = "drink_explain")
    private String drinkExplain;
    @Column(name = "store_phone")
    private String storePhone;
    
    @Column(name = "store_time")
    private String storeTime;

	public Store update(String storeName, String storeAddress, String storePhone, String storeTime, String drinkExplain) {
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.storeTime = storeTime;
		this.drinkExplain = drinkExplain;
		
		return this;
	}
    
    
}
