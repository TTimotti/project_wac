package com.wac.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Menu;
import com.wac.dto.MenuCreateDto;
import com.wac.dto.MenuReadDto;
import com.wac.repository.MenuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@RequiredArgsConstructor
public class MenuService {
    
    private final MenuRepository menuRepository;
    
    @Transactional(readOnly = true)
    public List<MenuReadDto> readMenus(Integer kind) {
        log.info("readMenus");
        
        List<Menu> list = menuRepository.readAllList(kind);
        
        return list.stream().map(MenuReadDto::fromEntity).toList();
    }

    public Menu create(MenuCreateDto dto) {
        log.info("create(dto={}", dto);
        
        Menu menu = Menu.builder()
                .menuName(dto.getMenuName())
                .kind(dto.getKind())
                .content(dto.getContent())
                .price(dto.getPrice())
                .build();
        
        Menu entity = menuRepository.save(menu);
        
        return entity;
    }

    public Integer countMenus(Integer kind) {
        Integer menus = menuRepository.countByKind(kind);
        return menus;
    }
    
}
