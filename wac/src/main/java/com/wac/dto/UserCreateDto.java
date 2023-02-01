package com.wac.dto;

import com.wac.domain.Users;
import com.wac.domain.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user 생성 dto.
 * 
 * userName, userPassword, phone, address, gender, age, email을 입력받아서 데이터 생성
 * 
 * @author 이존규
 *
 */
@NoArgsConstructor
@Data
public class UserCreateDto {

	private String userName; // 로그인에 사용할 이름
	private String userPassword; // 로그인에 사용할 비밀번호
	private String phone; // 개인정보 확인을 위한 휴대폰 번호

	private String email; // 아이디 인증, 비밀번호 찾기 등의 기능을 위한 email

	private String address; // 주문시, 배달지역 확인을 위한 주소 (다음 api를 통한 값 받기)
	private String address2; // 상세주소 (직접 입력한 값 받기)

	private Integer age; // 데이터 분석에 사용할 나이
	private String gender; // 데이터 분석에 사용할 성별

	public Users toEntity() {

		if (userName.equals("admin")) {
			return Users.builder().userName(userName).userPassword(userPassword).phone(phone).email(email)
					.address(address + " " + address2).age(age).gender(gender).build().addRole(UserRole.USER).addRole(UserRole.ADMIN);
		} else {
			return Users.builder().userName(userName).userPassword(userPassword).phone(phone).email(email)
					.address(address + " " + address2).age(age).gender(gender).build().addRole(UserRole.USER); 
		}
 
	}
}
