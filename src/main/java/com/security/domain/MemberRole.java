package com.security.domain;

import com.security.dto.SignupRequestDto;
import com.security.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {
	private String userId;
	private int roleId;
	private UserRole role;
	
	public MemberRole(String userId) {
		this.userId = userId;
		role = UserRole.USER;
		
		// role = UserRole.USER;	
		// 회원가입시 기본 권한은 USER로 설정
	}
}
