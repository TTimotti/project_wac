package com.wac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 영수증 상세내역 테이블
 * @author 추지훈
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Data
@ToString
@Entity(name = "ORDER_LOG")
@SequenceGenerator(name = "ORDER_LOG_SEQ_GEN", sequenceName = "ORDER_LOG_SEQ", initialValue = 1, allocationSize = 1)
public class OrderLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_SEQ_GEN")
    @Column(name = "order_log_id")
    private Integer orderLogId; // 1, 2, 3
    
    @Column(name = "order_id")
    private Integer orderId; // 1
        
    @Column(name = "user_id")
    private Integer userId; // 주문한 회원 아이디
    
    @Column(name = "user_name")
    private String userName; // 주문한 회원 아이디
    
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
    
    @Column // default: 1
    private Integer quantity; // 수량
    
    @Column
    private Integer image; // 메뉴 이미지
    
    @Column
    private Integer price1;
    
    @Column
    private Integer price2;
    
    @Column
    private Integer price3;
    
    @Column
    private Integer price4;
    
    @Column
    private Integer price5;
    
    @Column
    private Integer price6;
    
    
//    @Column(name = "order_indate") // 크리에이트 모디파이 날짜로 해결될듯해서 주석처리 하겠습니다. 
//    private Integer orderIndate; // 주문 날짜 
    
    
    
}
