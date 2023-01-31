package com.wac.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * UserId를 PK로 갖는 USERS 테이블. UserName = 로그인 할 때 사용할 아이디로, Unique 부여. 회원가입, 비밀번호
 * 찾기 등의 기능으로 사용하기 위하여 email을 not-null 데이터 분석에 사용하기 위하여 gender, age, address,
 * time(BaseTimeEntity) 값을 받음
 * 
 * @author 이존규
 *  
 */
@Entity(name = "USERS")
@SequenceGenerator(name = "USERSA_SEQ_GEN", sequenceName = "USERSA_SEQ", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter 
@ToString
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSA_SEQ_GEN")
    private Integer userId; // 개인마다 고유한 번호 부여. => CRUD에 사용

    @Column(unique = true, nullable = false)
    private String userName; // 로그인에 사용할 이름(아이디)

    @Column(nullable = false)
    private String userPassword; // 로그인에 사용할 비밀번호

    @Column(nullable = false)
    private String email; // 아이디 인증, 비밀번호 찾기 등의 기능을 위한 email

    @Column(nullable = false)
    private String phone; // 개인정보 확인을 위한 휴대폰 번호

    @Column(nullable = false)
    private String address; // 배달주소 확인 및 데이터 분석에 사용할 주소

    @Column(nullable = false)
    private String gender; // 데이터 분석에 사용할 나이

    @Column(nullable = false)
    private Integer age; // 데이터 분석에 사용할 성별

    /**
     * Spring security, 관리자 기능을 사용하기 위한 roles settring
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roles = new HashSet<>();

    /**
     * 회원가입 한 유저들에게 Role 을 부여. Default = USER
     * 
     * @param role (USER or ADMIN)
     * @return default = USER
     * @author 이존규
     */
    public Users addRole(UserRole role) {
        roles.add(role);

        return this;
    }
    
    /**
     * 정보 변경 시 사용할 메서드
     * @param 변경할 값들
     * @return 기존의 값들을 변경할 값들로 바꿈
     * @author 이존규
     */
    public Users update(String email, String phone, String address, String gender, Integer age) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.age = age;
        
        return this;
    }
    
    /**
     * 비밀번호 변경 시 사용할 메서드
     * @param password 변경하고자 하는 비밀번호
     * @return 기존의 비밀번호를 변경할 비밀번호로 변경
     * @author 이존규
     */
    public Users passwordChange(String password) {
        this.userPassword = password;
        
        return this;
    }
}
