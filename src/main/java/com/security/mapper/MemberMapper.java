package com.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.security.domain.Member;
import com.security.domain.MemberRole;

@Mapper
public interface MemberMapper {
	
	// 회원가입 부분
	public void insertMember(Member member);
	public void insertMemberRole(MemberRole memberRole);
	public int existMemberById(String userId);
	
	public Member findMemberById(String userId);
	public MemberRole findMemberRoleById(String userId);
}
