package com.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
	private String userId;
	private String email;
	private String name;
	private String password;
}
