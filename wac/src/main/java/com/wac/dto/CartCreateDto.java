package com.wac.dto;

import com.wac.domain.Cart;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class CartCreateDto {

    private Integer menuId;
    private Integer userId;
    private String address;
    
    public Cart toEntity() {
        return Cart.builder()
                .menuId(menuId)
                .userId(userId)
                .address(address)
                .build();
    }
}
