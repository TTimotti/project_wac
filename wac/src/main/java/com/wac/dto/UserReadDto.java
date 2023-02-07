package com.wac.dto;

import com.wac.domain.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * user read dto.
 * 
 * userId, userName, phone, email, address, age, gender 의 정보를 가진 객체
 * 
 * @author 김지훈
 *
 */
@AllArgsConstructor
@Data
@Builder
public class UserReadDto {

	private Integer userId; 
	private String userName; 
	private String phone; 
	private String email; 
	private String address; 
	private Integer age;
	private String gender;

	public static UserReadDto fromEntity(Users user) {
		return UserReadDto.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.phone(user.getPhone())
				.email(user.getEmail())
				.address(user.getAddress())
				.age(user.getAge())
				.gender(user.getGender())
				.build();
 
	}
}
