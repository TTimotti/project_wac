package com.wac.dto;

import javax.persistence.Entity;

import com.wac.domain.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserUpdateDto {

    private Integer userId;

    private String email;

    private String phone;

    private String address;         // 주문시, 배달지역 확인을 위한 주소 (다음 api를 통한 값 받기)
    private String address2;        // 상세주소 (직접 입력한 값 받기)

    private String gender;
    
    private Integer age;

    public Users toEntity() {
        return Users.builder().userId(userId).email(email).gender(gender).phone(phone).address(address + ' ' + address2).age(age).build();
    }
}
