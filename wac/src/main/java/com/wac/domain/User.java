package com.wac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * USERS DOMAIN
 * 
 * UserId를 PK로 갖는 USERS 테이블.
 * UserName = 로그인 할 때 사용할 아이디로, Unique 부여.
 * 회원가입, 비밀번호 찾기 등의 기능으로 사용하기 위하여 email을 not-null
 * 데이터 분석에 사용하기 위하여 gender, age, address, time(BaseTimeEntity) 값을 받음
 * 
 * @author 이존규
 *
 */
@Entity(name = "USERS")
@SequenceGenerator(name ="USERA_SEQ_GEN", sequenceName = "USERA_SEQ", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERA_SEQ_GEN")
    private Integer userId;     // 개인마다 고유한 번호 부여. => CRUD에 사용
    
    @Column(unique = true, nullable = false)  
    private String userName;    // 로그인에 사용할 이름(아이디)
    
    @Column(nullable = false)
    private String userPassword;    // 로그인에 사용할 비밀번호
    
    @Column(nullable = false)
    private String email;       // 아이디 인증, 비밀번호 찾기 등의 기능을 위한 email
    
    @Column(nullable = false)
    private String phone;       // 개인정보 확인을 위한 휴대폰 번호
    
    @Column(nullable = false)
    private String address;     // 배달주소 확인 및 데이터 분석에 사용할 주소
    
    @Column(nullable = false)
    private String gender;      // 데이터 분석에 사용할 나이
            
    @Column(nullable = false)
    private Integer age;        // 데이터 분석에 사용할 성별
        
    
}
