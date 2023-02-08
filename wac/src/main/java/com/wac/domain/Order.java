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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_LOG_SEQ_GEN")
    @Column(name = "order_id")
    private Integer orderId; // userId seq => 유저를 구분하는 seq값 일렬번호같은거
    
//    @Column(name = "order_num") // 주문 번호로 쓰고
//    private Integer orderNum; // 000011 userName => 회원가입할때 아이디같은거  
    
    @Column(name = "pickup_service")
    private Integer pickupService; // 매장식사: 1, 테이크아웃: 2, 배달: 3, DT: 4

    @Column
    private Integer payment; // 결제 수단  카드: 1, 현금 : 2
    
    @Column(name = "user_id")
    private Integer userId; // 사용자    
    
    @Column(name = "store_id")
    private Integer storeId;
    
    @Column(name = "store_name")
    private String storeName;
    
    @Column(name = "address")
    private String address;
    
}
