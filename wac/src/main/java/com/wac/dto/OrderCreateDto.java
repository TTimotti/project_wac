package com.wac.dto;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class OrderCreateDto {

    private Integer cartNum;
    private Integer menuId;
    private Integer userId;
    private Integer quantity;
    
//    public static OrderCreateDto ( ) {
}
