package com.security.authmember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.domain.Member;
import com.security.domain.MemberRole;
import com.security.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	//UserDetailsService : 유저의 정보를 가져오는 인터페이스
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		int exist = memberMapper.existMemberById(username);
		if(exist == 1)
			new UsernameNotFoundException("등록되지 않은 사용자");
		
		Member member = memberMapper.findMemberById(username);
		MemberRole memberRole = memberMapper.findMemberRoleById(username);
		
		
		return new UserDetailsImpl(memberRole,member);
	}
	
}
