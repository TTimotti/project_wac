package com.wac.dto;

import com.wac.domain.Menu;

import lombok.Builder;
import lombok.Data;

@Builder 
@Data
public class MenuReadDto {

    private Integer menuId;
    private String menuName;
    private Integer price;
    private Integer kind;
    private String content;
    private Integer image;
    
    public static MenuReadDto fromEntity(Menu entity) {
        return MenuReadDto.builder()
                .menuId(entity.getMenuId())
                .menuName(entity.getMenuName())
                .price(entity.getPrice())
                .content(entity.getContent())
                .kind(entity.getKind())
                .image(entity.getImage())
                .build();
    }
    
}
