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

    private String address;

    private String gender;
    
    private Integer age;

    public Users toEntity() {
        return Users.builder().userId(userId).email(email).gender(gender).phone(phone).address(address).age(age).build();
    }
}
