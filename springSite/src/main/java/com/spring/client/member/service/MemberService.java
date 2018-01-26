package com.spring.client.member.service;

import com.spring.client.member.vo.MemberVO;

public interface MemberService {

	public int userIdConfirm(String userId);

	public MemberVO memberSelect(String userId);

	public int memberInsert(MemberVO mvo);

	public boolean memberUpdate(MemberVO mvo);

	public int memberDelete(String userId);

}
