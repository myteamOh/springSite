package com.spring.client.member.dao;

import com.spring.client.member.vo.MemberSecurity;
import com.spring.client.member.vo.MemberVO;

public interface MemberDao {
	public int securityInsert(MemberSecurity set);

	public MemberSecurity securitySelect(String userId);

	public int securityDelete(String userId);

	public MemberVO memberSelect(String userId);

	public int memberInsert(MemberVO mvo);

	public int memberUpdate(MemberVO mvo);

	public int memberDelete(String userId);
}
