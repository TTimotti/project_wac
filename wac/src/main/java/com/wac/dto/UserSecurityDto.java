package com.wac.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.wac.domain.Users;

public class UserSecurityDto extends org.springframework.security.core.userdetails.User {

    private String userName;
    private String userPassword;

    public UserSecurityDto(String userName, String userPassword, Collection<? extends GrantedAuthority> authorities) {
        super(userName, userPassword, authorities);

        this.userName = userName;
        this.userPassword = userPassword;

    }

    public static UserSecurityDto fromEntity(Users u) {
        List<GrantedAuthority> authorities = u.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRole()))
                .collect(Collectors.toList());
        
        UserSecurityDto dto = new UserSecurityDto(u.getUserName(), 
                u.getUserPassword(), authorities);
        
        return dto;
    }

}
