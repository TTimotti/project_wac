package com.wac.dto;

import com.wac.domain.User;

public class PasswordChangeDto {
    private Integer userId;
    
    private String userPassword;
    
    public User toEntity() {
        return User.builder().userId(userId).userPassword(userPassword).build();
    }
}
