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
@Entity(name = "ORDER_LOG")
@SequenceGenerator(name = "ORDER_LOG_SEQ_GEN", sequenceName = "ORDER_LOG_SEQ", initialValue = 1, allocationSize = 1)
public class OrderLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_LOG_SEQ_GEN")
    @Column(name = "order_log_id")
    private Integer orderLogId; 
    
    // 세가지 모두 2번(그렇지 않은 경우) == 매장식사  => 이건 이제 자료산출에 쓸 자료
    @Column
    private Integer driveThru; // DT로 가져가는 경우: 1, 그렇지 않은 경우 2
    
    @Column
    private Integer delivery; // 배달: 1, 그렇지않은 경우: 2 
    
    @Column
    private Integer isTakeOut; // 테이크아웃: 1, 그렇지않은 경우: 2
    
//    @Column(name = "delivery_time")     // 이게 원래 배달 도착 시간인데 이용도 말고
//    private LocalDateTime deliveryTime; // 배송에 걸리는 시간으로써서 그냥 몇십분 Integer 로 해도 되긴하는데 
                                          // 필요업을꺼같아서 주석처리 해놓겠습니다. 
//    @Column(name = "cart_id")
//    private Integer cartId; // 카트가 지워지고 주문내역이 남기때문에 오더아이디만 필요할 것 같습니다. 일단 주석처리 해놓겠습니다.
    
    @Column(name = "user_id")
    private Integer userId; // 사용자    
    
    @Column(name = "store_id")
    private Integer storeId;
    
    @Column(name = "store_name")
    private String storeName;
    
    @Column(name = "address")
    private String address;
    
    
}
