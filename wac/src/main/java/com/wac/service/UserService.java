package com.wac.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wac.domain.Users;
import com.wac.dto.PasswordChangeDto;
import com.wac.dto.UserCreateDto;
import com.wac.dto.UserReadDto;
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
     * 모든 User의 정보를 return
     * @return 모든 User의 정보
     * @author 이존규
     */
    @Transactional(readOnly = true)
    public List<Users> read() {
        log.info("read all");
        
        return userRepository.findByOrderByUserIdDesc();
    }
    /**
     * User의 정보를  UserReadDto로 만들어 return
     * @return UserReadDto
     * @author 김지훈
     */
    @Transactional(readOnly = true)
    public List<UserReadDto> readUserList() {
    	List<Users> users = userRepository.findByOrderByUserIdDesc();
    	List<UserReadDto> userList = new ArrayList<UserReadDto>();
    	for (Users u : users) {
    		userList.add(UserReadDto.fromEntity(u));
    	}
        
        return userList;
    }
    
    /**
     * User Road 기능. userId를 입력받으면 id와 일치하는 user의 정보를 return 
     * @param userId
     * @return User 객체
     */
    @Transactional(readOnly = true)
    public Users read(Integer userId) {
        log.info("User read(userId = {})", userId);
        
        return userRepository.findById(userId).get();
    }
    
    /**
     * User Road 기능. userName를 입력받으면 userName과 일치하는 user의 정보를 return 
     * @param userName
     * @return User 객체
     */
    @Transactional(readOnly = true)
    public Users read(String userName) {
        log.info("User read(userId = {})", userName);
        
        return userRepository.findByUserName(userName).get();
    }
    
    /**
     * User Create 기능. user 생성에 필요한 정보를 입력받고, userPassword는 암호화 하여 저장.
     * @param user Create에 필요한 정보를 dto로 형태로 입력받음
     * @return 입력받은 dto의 userPassword를 암호화 하여 user 생성.
     */
    public Users createUser(UserCreateDto dto) {
        log.info("User Create(userCreate Dto = {})", dto);
        
        dto.setUserPassword(passwordEncoder.encode(dto.getUserPassword())); // 입력받은 비밀번호를 암호화
        Users entity = userRepository.save(dto.toEntity());
        
        log.info("entity = {}", entity);
        
        return entity;
    }

    
    /**
     * User Update 기능.
     * 입력받은 Email, Phone, Address, Gender, Age정보를 해당하는 ID의 유저의 기존 정보에 덮어씌움
     * @param userId, email, phone, address, gender, age 정보를 담은 dto
     * @return 최신화 된 유저의 Id값
     */
    @Transactional
    public Integer update(UserUpdateDto dto) {
        log.info("user update (dto) ={}",dto);
        
        Users entity = userRepository.findById(dto.getUserId()).get();
        
        Users u = dto.toEntity();
        
        entity.update(u.getEmail(), u.getPhone(), u.getAddress(), u.getGender(), u.getAge());
        
        return entity.getUserId();
    }
    

	
    /**
     * 비밀번호 변경 기능
     * 입력받은 password를 기존의 password와 변경
     * @param dto 변경하고자 하는 user id, password
     * @return
     */
    @Transactional
    public Integer passwordChange(PasswordChangeDto dto) {
        log.info("password change service dto = {}", dto);
        
        dto.setChangePassword(passwordEncoder.encode(dto.getChangePassword()));
        
        Users entity = userRepository.findById(dto.getUserId()).get();
        
        log.info("before change ={}", entity.getUserPassword());
        
        entity.passwordChange(dto.getChangePassword());
        
        log.info("after change = {}", entity.getUserPassword());
        
        return dto.getUserId();
    }
    
    /**
     * User delete 기능
     * userId를 입력받아서, 해당 userId를 가진 User 정보를 DB에서 삭제
     * @param userId
     * @return 삭제된 userId 값
     */
    public Integer delete(Integer userId) {
        log.info("delete");
        
        userRepository.deleteById(userId);
        
        return userId;
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
        
        Users user = userRepository.findById(userId).get();
        
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
    
    /**
     * 기존에 입력받은 Id 값으로 DB에서 검색.
     * @param userName 입력받은 ID값
     * @return 일치하는 ID가 있으면 nok, 일치하는 ID가 없으면 ok를 return
     */
    public String checkUsername(String userName) {
        log.info("checkUsername(username={})", userName);

        Optional<Users> result = userRepository.findByUserName(userName);
        if (result.isPresent()) { // username이 일치하는 생성된 객체가 존재하는 경우.
            return "nok";
        } else { // username이 일치하는 생성된 객체가 존재하지 않는 경우.
            return "ok";
        }
    }

    /**
     * 결제 할때 아이디 찾아야함 
     * @param userName
     * @return
     * @author 추지훈
     */
    public Integer getUserIdByUserName(String userName) {
        log.info("유저이름으로 아이디불러오기 = {} ", userName);
        return userRepository.findUserIdByUserName(userName);
    }
}
