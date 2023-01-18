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
@Entity(name = "ORDER_DETAIL")
@SequenceGenerator(name = "ORDERS_DETAIL_SEQ_GEN", sequenceName = "ORDERS_DETAIL_SEQ", initialValue = 1, allocationSize = 1)
public class OrderDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERS_DETAIL_SEQ_GEN")
    @Column(name = "order_detail_id")
    private Integer ordetailId; 
    
    @Column(name = "order_id")
    private Integer orderId; // 주문 번호 orders에 아이디값
    
    @Column(name = "prod_num")
    private Integer prodNum; // 상품 번호 
    
    @Column
    private Integer quantity; // 수량
    
    @Column(name = "shipping")
    private String shipping; // 배송 전: 1, 배송 후: 2
    
}