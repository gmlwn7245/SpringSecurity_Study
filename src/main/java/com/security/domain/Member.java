package com.security.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.dto.SignupRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
	
	private String userId;
	private String password;
	private String email;
	private String name;
	
//	@Enumerated(EnumType.STRING)
//	private UserRole role;
	
	public Member(SignupRequestDto requestDto) {
		userId = requestDto.getUserId();
		name = requestDto.getName();
		email = requestDto.getEmail();
		password = requestDto.getPassword();
		
		// role = UserRole.USER;	
		// 회원가입시 기본 권한은 USER로 설정
	}
	
	
	
}
