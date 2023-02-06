package com.wac.dto;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class CartTossDto {

    private Integer menuId;
    private String userName;
    private String storeName;
    private String userAddress;

}
