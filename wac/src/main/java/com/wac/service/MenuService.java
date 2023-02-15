package com.wac.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Cart;
import com.wac.domain.Menu;
import com.wac.dto.MenuCreateDto;
import com.wac.dto.MenuReadDto;
import com.wac.dto.MenuUpdateDto;
import com.wac.dto.MyCartMenuDto;
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
    public Menu readMenu(Integer menuId) {
        log.info("readMenu menuId = {}", menuId);
        Menu menu = menuRepository.findByMenuId(menuId).get();
        
        return menu;
    }
    

    /**
     * 메뉴 생성 메서드
     * @param dto
     * @return
     * @author 추지훈
     */
    public Menu create(MenuCreateDto dto, Integer fid) {
        log.info("Menu.create(dto={}, file = {})", dto, fid);
        String sellyn = "y"; // 생성하면 판매중인 메뉴러 기본 설정. 
        
        Menu menu = Menu.builder()
                .menuName(dto.getMenuName())
                .menuEnName(dto.getMenuEnName())
                .kind(dto.getKind())
                .content(dto.getContent())
                .price(dto.getPrice())
                .image(fid)
                .sellyn(sellyn)
                .build();
        
        Menu entity = menuRepository.save(menu);
        
        return entity;
    }

    /**
     * 메뉴 개수 세는 메서드
     * @param kind
     * @return
     * @author 추지훈
     */
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
    public Menu readPrevMenuById(Integer kind, Integer menuId) {
        log.info("readPrevMenuById(kind={}, menuId={})", kind, menuId);
        return menuRepository.readPrevMenuById(kind, menuId);
    }
    
    /**
     * 메뉴 상세 페이지에서 다음 메뉴 보여주기.
     * @param menuId
     * @return next 메뉴를 보여줍니다.
     * @author 서범수
     */
    public Menu readNextMenuById(Integer kind, Integer menuId) {
        log.info("readNextMenuById(kind={}, menuId={})", kind, menuId);
        return menuRepository.readNextMenuById(kind, menuId);
    }

    /**
     * 세트메뉴에서 버거 가져오는 메서드
     * @param data_menuId
     * @return
     * @author 추지훈
     */
    public Integer readBugerByMeal(Integer data_menuId) {
        log.info("세트메뉴에서 버거 가져오는 메서드 호출()", data_menuId);
        String bugerName = menuRepository.readBugerNameByMealId(data_menuId);
        log.info("bugerName ={}", bugerName);
        Integer menuId = menuRepository.readBugerIdByMealName(bugerName);
        log.info("menuId ={}", menuId);
        return menuId;
    }


    /**
     * 메뉴 업데이트
     * @param dto
     * @return
     * @author 추지훈
     */
    @Transactional
    public Integer update(MenuUpdateDto dto) {
        log.info("postService update(dto={})", dto);
        
        Menu entity = menuRepository.findById(dto.getId()).get(); // (1)
        entity.update(dto.getMenuName(), dto.getMenuEnName(), dto.getKind() , dto.getPrice(), dto.getContent()); // (2)
        
        log.info("entity = {}",entity);
        
        return entity.getMenuId();
    }
    
    
    public List<Menu> readAllByMenuId(List<Cart> cartList) {
        ArrayList<Integer> cartListBymenuId = new ArrayList<>();
        
        for (Cart c : cartList) {
            log.info("x={}", cartList.get(1));
        }
        
        log.info("readAllByMenuId={}", cartList);
        
        return null;

    }

    /**
     * 메뉴아이디로 분류번호 반환해주는 메서드
     * @param menuId
     * @return
     */
    @Transactional(readOnly = true)
    public Integer readMenuByKind(Integer menuId) {
        log.info("readMenu menuId = {}", menuId);
        Integer kind = menuRepository.findByKind(menuId);
        
        return kind;
    }

    public Integer delete(Integer id) {
        log.info("menu delete(id={})", id);
        
        menuRepository.deleteById(id);
        
        return id;
    }
    
    
    /**
     * "코카-콜라", "후렌치 후라이" 라는 이름의 메뉴를 찾아서 해당하는 menuId를 가져옴.
     * @param name "코카-콜라" 또는 "후렌치 후라이"
     * @return menuId
     * @author 서범수
     */
    public Integer readDefaultOptionByName(String name) {
        log.info("readDefaultOptionByName(cokeName={})", name);
        
        Integer menuId = menuRepository.readDefaultOptionByName(name);
        return menuId;
    }
    
    /**
     * 카트에 담겨있는 정보들 한 번에 가져오기.
     * @param menuIdList
     * @return
     * @author 서범수.
     */
    public ArrayList<MyCartMenuDto> getMenuInfo(ArrayList<Integer> menuIdList) {
        for (Integer menuId : menuIdList) {
        log.info("getMenuInfo(menuIdList={})", menuId);
        }

        // menuIdList에 있는 '순서대로' 가져와야 함.
        ArrayList<MyCartMenuDto> menus = new ArrayList<>();

        for (Integer menuId : menuIdList) {
            MyCartMenuDto menu = menuRepository.getMenuInfo(menuId);
            menus.add(menu);
        }

        log.info("메뉴메뉴={}", menus);
        return menus;
    }

    public Integer readDefaultPriceByName(String name) {
        Integer price = menuRepository.readDefaultPriceByName(name);
        return price;
    }
    
    


    
}
