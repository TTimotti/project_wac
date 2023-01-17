package com.wac.domain;

import java.time.LocalDateTime;

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
    
    @Column(name = "user_id")
    private Integer userId; // 주문한 회원 아이디
    
    @Column(name = "order_indate")
    private Integer orderIndate; // 주문 날짜
    
}
