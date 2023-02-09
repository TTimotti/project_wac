package com.wac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyCartMenuDto {
    private Integer menuId;
    private Integer image;
    private Integer kind;
    private String menuName;
    private Integer price;
    
    public MyCartMenuDto toEntity() {
        return MyCartMenuDto.builder().menuId(menuId).image(image).kind(kind).menuName(menuName).price(price).build();
    }
}
