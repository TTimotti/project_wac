package com.wac.dto;

import com.wac.domain.Cart;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class CartTossDto {

    private Integer menuId;
    private String userName;
//    private String address;
    
//    public Cart toEntity() {
//        return Cart.builder()
//                .menuId1(menuId1)
//                .menuId2(menuId2)
//                .menuId3(menuId3)
//                .menuId4(menuId4)
//                .userId(userId)
//                .address(address)
//                .build();
//    }
}
