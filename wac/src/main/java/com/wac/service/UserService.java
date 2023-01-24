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
     * 모든 User의 정보를 return
     * @return 모든 User의 정보
     * @author 이존규
     */
    @Transactional(readOnly = true)
    public List<User> read() {
        log.info("read all");
        
        return userRepository.findByOrderByUserIdDesc();
    }
    
    /**
     * 입력받은 Email, Phone, Address, Gender, Age정보를 해당하는 ID의 유저의 기존 정보에 덮어씌움
     * @param userId, email, phone, address, gender, age 정보를 담은 dto
     * @return 최신화 된 유저의 Id값
     */
    @Transactional
    public Integer update(UserUpdateDto dto) {
        log.info("user update (dto) ={}",dto);
        
        User entity = userRepository.findById(dto.getUserId()).get();
        
        entity.update(dto.getEmail(), dto.getPhone(), dto.getAddress(),dto.getGender(), dto.getAge());
        
        return entity.getUserId();
    }
    
    /**
     * 입력받은 2개의 비밀번호를 확인
     * @param userId 입력받은 유저의 id값
     * @param password 입력받은 비밀번호 값
     * @return DB에 저장된 userId와 일치하는 password 값을 일치하면 ok, 일치하지 않으면 nok를 return
     * @author 이존규
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
     * @return 2개의 비밀번호를 비교하여 일치하면 true, 불일치하면 false를 return
     * @author 이존규
     */
	public boolean confirm(String password, String password2) {
		return passwordEncoder.matches(password, password2);
	}
    
}
