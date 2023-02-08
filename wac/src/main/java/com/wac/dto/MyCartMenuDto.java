package com.wac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCartMenuDto {
    private Integer cartId;
    private Integer userId;
    private Integer menuId;
    private Integer image;
    private Integer kind;
    private String menuName;
    private Integer price;
}
