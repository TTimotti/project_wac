package com.wac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wac.domain.Menu;

/**
 * @author 추지훈
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    
    List<Menu> findByKind(String kind);
    
    List<Menu> findByMenuIdOrderByMenuId(Integer menuId);

    @Query("select m from MENUS m where m.kind = :kind")
    List<Menu> readAllList(@Param("kind") Integer kind);

    /**
     * 분류별 베뉴 개수 확인
     * @param kind
     * @return
     */
    @Query("select count(m.kind) from MENUS m where m.kind = :kind")
    Integer countByKind(Integer kind);
}
