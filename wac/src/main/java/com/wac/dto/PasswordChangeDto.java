package com.wac.dto;

import com.wac.domain.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PasswordChangeDto {
    private Integer userId;
    
    private String changePassword;
    
    public Users toEntity() {
        return Users.builder().userId(userId).userPassword(changePassword).build();
    }
}
