package com.security.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.LoginRequestDto;
import com.security.dto.SignupRequestDto;
import com.security.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
	private AuthService authService;
	
	@PostMapping(value="login")
	public String login(@RequestBody LoginRequestDto reqDto) {
		
		try {
			return authService.login(reqDto);
		} catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
    @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signup(@RequestBody SignupRequestDto request) {
        return authService.signup(request);
    }
	
}
