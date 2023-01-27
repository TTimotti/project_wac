package com.wac.dto;

import com.wac.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PasswordChangeDto {
    private Integer userId;
    
    private String changePassword;
    
    public User toEntity() {
        return User.builder().userId(userId).userPassword(changePassword).build();
    }
}
