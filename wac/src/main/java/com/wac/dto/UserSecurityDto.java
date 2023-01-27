package com.wac.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.wac.domain.Users;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSecurityDto extends org.springframework.security.core.userdetails.User {

    private String username;
    private String password;

    public UserSecurityDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.username = username;
        this.password = password;

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
