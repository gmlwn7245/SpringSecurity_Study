package com.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.authmember.UserDetailsImpl;
import com.security.domain.Member;
import com.security.domain.MemberRole;
import com.security.dto.LoginRequestDto;
import com.security.dto.SignupRequestDto;
import com.security.mapper.MemberMapper;


@Service
public class AuthService {
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
	
	//회원가입 부분
	public String signup(SignupRequestDto reqDto) {
		System.out.println("========success========");
		int existMember = memberMapper.existMemberById(reqDto.getUserId());
		
		//이미 존재하는 회원
		if(existMember==1) return null;
		
		//존재하지 않은 회원
		Member member = new Member(reqDto);
		member.setPassword(passwordEncoder.encode(reqDto.getPassword()));
		memberMapper.insertMember(member);
		
		MemberRole memberRole = new MemberRole(reqDto.getUserId());
		memberMapper.insertMemberRole(memberRole);
		
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
