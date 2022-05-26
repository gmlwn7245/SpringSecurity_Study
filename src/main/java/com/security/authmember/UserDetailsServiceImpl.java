package com.security.authmember;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.domain.Member;
import com.security.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	//UserDetailsService : 유저의 정보를 가져오는 인터페이스
	
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findById(username)
				.orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자"));
		return new UserDetailsImpl(member);
	}
	
}
