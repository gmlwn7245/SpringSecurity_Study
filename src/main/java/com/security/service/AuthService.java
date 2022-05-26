package com.security.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.authmember.UserDetailsImpl;
import com.security.domain.Member;
import com.security.dto.LoginRequestDto;
import com.security.dto.SignupRequestDto;
import com.security.repository.MemberRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class AuthService {
	private MemberRepository memberRepository;
	private PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authManager;
	
	//회원가입 부분
	public String signup(SignupRequestDto reqDto) {
		boolean existMember = memberRepository.existsById(reqDto.getUserId());
		
		//이미 존재하는 회원
		if(existMember) return null;
		
		//존재하지 않은 회원
		Member member = new Member(reqDto);
		member.encryptPassword(passwordEncoder);
		
		
		memberRepository.save(member);
		return member.getUserId();
	}
	
	
	//로그인 부분
	public String login(LoginRequestDto reqDto) {
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(reqDto.getUserId(), reqDto.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
				
		return principal.getUsername();
	}
}
