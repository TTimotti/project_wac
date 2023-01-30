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
                .menuEnName(dto.getMenuEnName())
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
    
    /**
     * 메뉴 상세 페이지
     * @param menuId
     * @author 서범수
     * @return
     */
    public Menu readById(Integer menuId) {
        log.info("readById(menuId={})", menuId);
        return menuRepository.findById(menuId).get();
    }
    
    /**
     * 메뉴 상세 페이지에서 이전 메뉴 보여주기.
     * @param menuId
     * @return prev 메뉴를 보여줍니다.
     * @author 서범수
     */
    public Menu readPrevMenuById(Integer menuId) {
        log.info("readNextMenuById={}", menuId);
        return menuRepository.readPrevMenuById(menuId);
    }
    
    /**
     * 메뉴 상세 페이지에서 다음 메뉴 보여주기.
     * @param menuId
     * @return next 메뉴를 보여줍니다.
     * @author 서범수
     */
    public Menu readNextMenuById(Integer menuId) {
        log.info("readNextMenuById={}", menuId);
        return menuRepository.readNextMenuById(menuId);
    }
    
}
