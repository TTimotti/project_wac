package com.wac.dto;

import groovy.transform.builder.Builder;
import lombok.Data;

@Builder
@Data
public class OrderTossDto {

    private Integer cartId;
    private String userName;
    private String storeName;
    private String userAddress;
    private Integer driveThru; // DT로 가져가는 경우: 1, 그렇지 않은 경우 2
    private Integer delivery; // 배달: 1, 그렇지않은 경우: 2 
    private Integer isTakeOut; // 테이크아웃: 1, 그렇지않은 경우: 2

}
