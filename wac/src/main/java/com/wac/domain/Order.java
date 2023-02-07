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
@Entity(name = "ORDERS")
@SequenceGenerator(name = "ORDERS_SEQ_GEN", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GEN")
    @Column(name = "order_id")
    private Integer orderId; 
    
    @Column(name = "order_log_id")
    private Integer orderLogId; 
    
    @Column(name = "user_id")
    private Integer userId; // 주문한 회원 아이디
    
    @Column(name = "user_name")
    private Integer userName; // 주문한 회원 아이디
    
    @Column(name = "cart_id")
    private Integer cartId; // 주문한 회원 장바구니 아이디
    
    @Column(name = "menu1_id") // 단품 
    private Integer menuId1; // 상품 번호 
    
    @Column(name = "menu2_id") // 세트: 세트일 경우 나머지 메뉴아이디 자동채움. 
    private Integer menuId2; // 상품 번호
    
    @Column(name = "menu3_id") // 사이드
    private Integer menuId3; // 상품 번호
    
    @Column(name = "menu4_id") // 음료
    private Integer menuId4; // 상품 번호
    
    @Column(name = "menu5_id") // 맥모닝
    private Integer menuId5; // 상품 번호
    
    @Column(name = "menu6_id") // 맥모닝 세트
    private Integer menuId6; // 상품 번호
    
    @Column
    private Integer image; // 메뉴 이미지
    
    @Column(name = "total_price")
    private Integer totalPrice;
    
//    @Column(name = "order_indate") // 크리에이트 모디파이 날짜로 해결될듯해서 주석처리 하겠습니다. 
//    private Integer orderIndate; // 주문 날짜 
    
    
    
}
