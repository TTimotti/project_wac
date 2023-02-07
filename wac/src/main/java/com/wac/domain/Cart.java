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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Data
@ToString
@Entity(name = "CARTS")
@SequenceGenerator(name = "CARTS_SEQ_GEN", sequenceName = "CARTS_SEQ", initialValue = 1, allocationSize = 1)
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTS_SEQ_GEN")
    @Column(name = "cart_id")
    private Integer cartId; // 고유번호, 장바구니 번호, 시퀀스생성
    
    @Column(name = "cart_num") // 이건 필요없는데 일단 두겠습니다. 
    private Integer cartNum; // 장바구니 한 장바구니에 여러 제품 담을 수 있음.
    
    @Column(name = "user_id") // userId를 cartNum 로 똑같이 쓰자 그러고 카트 지워버리기 
    private Integer userId; // 장바구니를 이용하는 회원
    
    @Column(name = "user_name") // userId를 cartNum 로 똑같이 쓰자 그러고 카트 지워버리기 
    private String userName; // 장바구니를 이용하는 회원
    
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
    
    @Column(name = "store_id")
    private Integer storeId; // 매장 아이디
    
    @Column(name = "store_name")
    private String storeName; // 매장 이름
    
    @Column // 매장선택하고 상세주소 가져오기
    private String address; // 주소
    
    @Column
    private Integer image; // 메뉴 이미지
    
//    @Column(name = "shipping") // default: 1
//    private Integer shipping; // 배송 전: 1, 배송 후: 2
    
//    @Column(name = "cart_indate")
//    private LocalDateTime cartIndate; // 장바구니에 담은 날짜 ? 이건 createdate로 통일가능한지 고민좀
}
