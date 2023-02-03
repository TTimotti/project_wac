package com.wac.dto;

import com.wac.domain.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuUpdateDto {

    private Integer menuId;
    private String menuName;
    private String menuEnName;
    private Integer kind;
    private Integer price;
    private String content;
    private Integer image;
    
    public Menu toEntity() {
        return Menu.builder().menuId(menuId).menuName(menuName).menuEnName(menuEnName).kind(kind).price(price).content(content).build();
    }
    
    public static MenuUpdateDto fromEntity(Menu entity) {
        return MenuUpdateDto.builder()
                .menuId(entity.getMenuId())
                .menuName(entity.getMenuName())
                .menuEnName(entity.getMenuEnName())
                .kind(entity.getKind())
                .price(entity.getPrice())
                .content(entity.getContent())
                .build();
    }
}
