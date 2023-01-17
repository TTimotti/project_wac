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
@Entity(name = "ORDER_LOG")
@SequenceGenerator(name = "ORDER_LOG_SEQ_GEN", sequenceName = "ORDER_LOG_SEQ", initialValue = 1, allocationSize = 1)
public class OrderLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_LOG_SEQ_GEN")
    @Column(name = "order_log_id")
    private Integer orderLogId; 
    
    // 세가지 모두 2번(그렇지 않은 경우) == 매장식사
    @Column
    private String driveThru; // DT로 가져가는 경우: 1, 그렇지 않은 경우 2
    
    @Column
    private String delivery; // 배달: 1, 그렇지않은 경우: 2 
    
    @Column
    private String isTakeOut; // 테이크아웃: 1, 그렇지않은 경우: 2
    
    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;
    
    @Column(name = "cart_id")
    private Integer cartId;
    
    @Column(name = "order_id")
    private Integer orderId;
    
    @Column(name = "total_price")
    private Integer totalPrice;
    
}
