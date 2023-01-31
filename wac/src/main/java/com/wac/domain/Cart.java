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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "CARTS")
@SequenceGenerator(name = "CARTS_SEQ_GEN", sequenceName = "CARTS_SEQ", initialValue = 1, allocationSize = 1)
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTS_SEQ_GEN")
    @Column(name = "cart_id")
    private Integer cartId; // 고유번호, 장바구니 번호, 시퀀스생성
    
    @Column(name = "cart_num")
    private Integer cartNum; // 장바구니 한 장바구니에 여러 제품 담을 수 있음.
    
    @Column(name = "user_id")
    private Integer userId; // 장바구니를 이용하는 회원
    
    @Column(name = "menuId")
    private Integer menuId; // 상품 번호 
    
    @Column
    private Integer quantity; // 수량
    
    @Column
    private String address; // 주소
    
    @Column(name = "shipping")
    private Integer shipping; // 배송 전: 1, 배송 후: 2
    
//    @Column(name = "cart_indate")
//    private LocalDateTime cartIndate; // 장바구니에 담은 날짜 ? 이건 createdate로 통일가능한지 고민좀
}
