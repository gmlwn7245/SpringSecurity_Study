package com.security.authmember;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.domain.Member;
import com.security.domain.MemberRole;
import com.security.model.UserRole;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	//UserDetails : 사용자 정보를 담는 인터페이스
	
	private MemberRole memberRole;
	private Member member;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UserRole role = memberRole.getRole();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleType());
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
