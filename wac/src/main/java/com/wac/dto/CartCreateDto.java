package com.wac.dto;

import com.wac.domain.Cart;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateDto {

    private Integer menuId1;
    private Integer menuId2;
    private Integer menuId3;
    private Integer menuId4;
    private Integer menuId5;
    private Integer menuId6;
    private Integer userId;
    private Integer quantity;
//    private String address;
    
    public Cart toEntity() {
        return Cart.builder()
                .menuId1(menuId1)
                .menuId2(menuId2)
                .menuId3(menuId3)
                .menuId4(menuId4)
                .menuId4(menuId5)
                .menuId4(menuId6)
                .userId(userId)
//                .address(address)
                .quantity(quantity)
                .build();
    }
}
