package com.wac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuSimpleDto {
    private Integer menuId;
    private String menuName;
    private Integer price;
    
    public MenuSimpleDto toEntity() {
        return MenuSimpleDto.builder().menuId(menuId).menuName(menuName).price(price).build();
    }
}
