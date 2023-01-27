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
    private Integer kind;
    private String price;
    private String content;
    private Integer image;
    
    public Menu toEntity() {
        return Menu.builder()
                .menuName(menuName)
                .kind(kind)
                .price(price) 
                .content(content)
                .image(image)
                .build();
    }
}
