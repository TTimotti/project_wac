package com.wac.dto;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class OrderTossDto {

    private String userName;
    private String storeName;
    private String userAddress;
    private Integer pickupService;
    private Integer payment;

    
}
