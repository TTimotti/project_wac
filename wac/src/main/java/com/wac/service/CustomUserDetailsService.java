package com.wac.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wac.domain.Users;
import com.wac.dto.UserSecurityDto;
import com.wac.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        // DB Members 테이블에서 사용자 정보를 검색해서 UserDetails 타입의 객체 리턴.
        // -> MemberSecurityDto 타입 객체를 생성해서 리턴
        // -> 일치하는 username(로그인 아이디)가 있으면 UserDetails 타입 (또는 하위 타입) 객체를 리턴.
        // -> 일치하는 username이 없으면 UsernameNotFoundException 예외를 발생. (절대 null을 return 시켜선 안됨)
     
        Optional<Users> entity = userRepository.findByUserName(userName);
        
        if(entity.isPresent()) {
        
            return UserSecurityDto.fromEntity(entity.get());
            
        } else {
            
            throw new UsernameNotFoundException(userName + ": not found 404");
            
        }
        
    }
    
}
