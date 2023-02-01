package com.wac.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.Menu;
import com.wac.dto.MenuReadDto;

/**
 * @author 추지훈
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    
    List<Menu> findByKind(String kind);
    
    List<Menu> findByMenuIdOrderByMenuId(Integer menuId);

    /**
     * 분류별 메뉴 리스트 가져오기
     * @param kind
     * @return
     * @author 추지훈
     */
    @Query("select m from MENUS m where m.kind = :kind order by m.menuId")
    List<Menu> readAllList(@Param("kind") Integer kind);

    /**
     * 분류별 베뉴 개수 확인
     * @param kind
     * @return
     */
    @Query("select count(m.kind) from MENUS m where m.kind = :kind")
    Integer countByKind(Integer kind);

    Optional<MenuReadDto> findByMenuId(Integer menuId);
    
    /**
     * 
     * @param menuId
     * @return prev 메뉴를 보여줍니다.
     * @author 서범수
     */
    // select * from menus where menu_id = (select max(menu_id) from menus where menu_id < 2) or menu_id = (select min(menu_id) from menus where menu_id > 2);..
    @Query("select m from MENUS m where m.menuId = (select max(m.menuId) from MENUS m where m.menuId < :menuId and m.kind = :kind)")
    Menu readPrevMenuById(@Param("menuId") Integer menuId, @Param("kind") Integer kind);
    
    /**
     * 
     * @param menuId
     * @return next 메뉴를 보여줍니다.
     * @author 서범수
     */
    // select * from menus where menu_id = (select max(menu_id) from menus where menu_id < 2) or menu_id = (select min(menu_id) from menus where menu_id > 2);..
    @Query("select m from MENUS m where m.menuId = (select min(m.menuId) from MENUS m where m.menuId > :menuId and m.kind = :kind)")
    Menu readNextMenuById(@Param("menuId") Integer menuId, @Param("kind") Integer kind);
}
