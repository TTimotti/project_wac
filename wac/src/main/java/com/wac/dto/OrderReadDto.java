package com.wac.dto;

import java.time.format.DateTimeFormatter;

import com.wac.domain.Order;

import lombok.Builder;
import lombok.Data;

@Builder 
@Data
public class OrderReadDto {

    private Integer orderId;
    private Integer userId;
    private Integer pickupService;
    private Integer payment;
    private Integer storeId;
    private String storeName;
    private String address;
    private Integer totalPrice;
    private String createdTime;
    
    public static OrderReadDto fromEntity(Order entity) {
        return OrderReadDto.builder()
                .orderId(entity.getOrderId())
                .userId(entity.getUserId())
                .pickupService(entity.getPickupService())
                .payment(entity.getPayment())
                .storeName(entity.getStoreName())
                .address(entity.getAddress())
                .createdTime(entity.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
