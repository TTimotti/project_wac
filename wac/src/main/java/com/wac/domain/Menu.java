package com.wac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 메뉴 테이블 
 * @author 추지훈
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "MENUS")
@SequenceGenerator(name = "MENUS_SEQ_GEN", sequenceName = "MENUS_SEQ", initialValue = 1, allocationSize = 1)
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENUS_SEQ_GEN")
    @Column(name = "menu_id")
    private Integer menuId;
    
    @Column(name = "menu_name")
    private String menuName;
    
    @Column(name = "menu_en_name")
    private String menuEnName;
    
    @Column
    private Integer kind; // 버거: 1, 세트: 2, 사이드: 3, 음료: 4, 맥모닝: 5, 맥모닝 세트: 6
    
    @Column
    private Integer price;
    
    @Column
    private String content;
    
    @Column
    private Integer image;
    
    @Column
    private String sellyn; // 판매 중: y , 판매중단: n
    
    
    public Menu update(String menuName, String menuEnName, Integer kind, Integer price, String content) {
        this.menuName = menuName;
        this.menuEnName = menuEnName;
        this.kind = kind;
        this.price = price; 
        this.content = content;
//        this.image = image;
        
        return this;
    }
}
