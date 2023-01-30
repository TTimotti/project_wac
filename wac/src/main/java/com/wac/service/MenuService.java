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
    
    /**
     * 전체 메뉴 불러오는 메서드
     * @param kind
     * @return
     * @author 추지훈
     */
    @Transactional(readOnly = true)
    public List<MenuReadDto> readMenus(Integer kind) {
        log.info("readMenus");
        
        List<Menu> list = menuRepository.readAllList(kind);
        
        return list.stream().map(MenuReadDto::fromEntity).toList();
    }
    
    /**
     * 메뉴 하나를 불러오는 메서드
     * 상세보기나 특정메뉴 검색이나 장바구니로 특정 메뉴를 보낼때 사용
     * @param menuId
     * @return
     * @author 추지훈
     */
    @Transactional(readOnly = true)
    public MenuReadDto readMenu(Integer menuId) {
        log.info("readMenu menuId = {}", menuId);
        
        return menuRepository.findByMenuId(menuId);
    }
    

    /**
     * 메뉴 생성 메서드
     * @param dto
     * @return
     * @author 추지훈
     */
    public Menu create(MenuCreateDto dto, Integer fid) {
        log.info("Menu.create(dto={}, file = {})", dto, fid);
        
        Menu menu = Menu.builder()
                .menuName(dto.getMenuName())
                .menuEnName(dto.getMenuEnName())
                .kind(dto.getKind())
                .content(dto.getContent())
                .price(dto.getPrice())
                .image(fid)
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
