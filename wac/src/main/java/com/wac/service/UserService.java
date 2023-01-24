package com.wac.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.User;
import com.wac.dto.UserCreateDto;
import com.wac.dto.UserUpdateDto;
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
    @Transactional(readOnly = true)
    public User read(Integer userId) {
        log.info("User read(userId = {})", userId);
        
        return userRepository.findById(userId).get();
    }
    
    /**
     * User Create 기능. user 생성에 필요한 정보를 입력받고, userPassword는 암호화 하여 저장.
     * @param user Create에 필요한 정보를 dto로 형태로 입력받음
     * @return 입력받은 dto의 userPassword를 암호화 하여 user 생성.
     */
    public User createUser(UserCreateDto dto) {
        log.info("User Create(userCreate Dto = {})", dto);
        dto.setUserPassword(passwordEncoder.encode(dto.getUserPassword())); // 입력받은 비밀번호를 암호화
        User entity = userRepository.save(dto.toEntity());
        
        log.info("entity = {}", entity);
        
        return entity;
    }
    
    /**
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> read() {
        log.info("read all");
        
        return userRepository.findByOrderByUserIdDesc();
    }
    
    /**
     * 
     * @param dto
     * @return
     */
    @Transactional
    public Integer update(UserUpdateDto dto) {
        log.info("user update (dto) ={}",dto);
        
        User entity = userRepository.findById(dto.getUserId()).get();
        
        entity.update(dto.getEmail(), dto.getPhone(), dto.getAddress(),dto.getGender(), dto.getAge());
        
        return entity.getUserId();
    }
    
    /**
     * 
     * @param userId
     * @param password
     * @return
     */
    public String checkPw(Integer userId, String password) {

		log.info("checkPw userid = {} password = {}", userId, password);
		
		User user = userRepository.findById(userId).get();
		
		log.info("ckpw user = {}", user);
		String enCodingPw = user.getUserPassword();
		
		log.info(enCodingPw);
		
		Boolean confirm = confirm(password, enCodingPw);
		
		
		log.info("confirm = {}", confirm);
		
		if (confirm == true) {
			return "ok";
		} else {
			return "nok";
		}
	
	}
	
    /**
     * 2개의 비밀번호를 비교하여 일치하면 true, 불일치하면 false를 return
     * @param password DB에 저장된 유저의 암호화 된 비밀번호
     * @param password2 유저가 입력한 비밀번호를 암호화한 값
     * @return
     */
	public boolean confirm(String password, String password2) {
		return passwordEncoder.matches(password, password2);
	}
    
}
