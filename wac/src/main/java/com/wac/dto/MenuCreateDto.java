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
public class MenuCreateDto {
    private String menuName;
    private String menuEnName;
    private Integer kind;
    private Integer price;
    private String content;
    
    public Menu toEntity() {
        return Menu.builder()
                .menuName(menuName)
                .menuEnName(menuEnName)
                .kind(kind)
                .price(price) 
                .content(content)
                .build();
    }
}
