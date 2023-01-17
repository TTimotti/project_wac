package com.wac.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wac.domain.User;
import com.wac.dto.userCreateDto;
import com.wac.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * User Service
 * @author 이존규
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service // 스프링 컨택스트에 service 컴포넌트로 등록.
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    /**
     * User Road 기능. userId를 입력받으면 id와 일치하는 user의 정보를 return 
     * @param userId
     * @return User 객체
     */
    public User read(Integer userId) {
        log.info("User read(userId = {})", userId);
        
        return userRepository.findById(userId).get();
    }
    
    /**
     * User Create 기능. user 생성에 필요한 정보를 입력받고, userPassword는 암호화 하여 저장.
     * @param user Create에 필요한 정보를 dto로 형태로 입력받음
     * @return 입력받은 dto의 userPassword를 암호화 하여 user 생성.
     */
    public User createUser(userCreateDto dto) {
        log.info("User Create(userCreate Dto = {})", dto);
        dto.setUserPassword(passwordEncoder.encode(dto.getUserPassword())); // 입력받은 비밀번호를 암호화
        
        User entity = userRepository.save(dto.toEntity());
        
        log.info("entity = {}", entity);
        
        return entity;
    }
    
}
