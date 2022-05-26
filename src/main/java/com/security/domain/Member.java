package com.security.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.dto.SignupRequestDto;
import com.security.model.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {
	@Id
	private String userId;
	
	private String password;
	private String email;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public Member(SignupRequestDto requestDto) {
		userId = requestDto.getEmail();
		name = requestDto.getName();
		email = requestDto.getEmail();
		password = requestDto.getPassword();
		
		role = UserRole.USER;	// 회원가입시 기본 권한은 USER로 설정
	}
	
	public void encryptPassword(PasswordEncoder passwordEncoder) {
		password = passwordEncoder.encode(password);
	}
	
	
}
