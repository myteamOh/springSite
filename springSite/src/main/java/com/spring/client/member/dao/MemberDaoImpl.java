package com.spring.client.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.client.member.vo.MemberSecurity;
import com.spring.client.member.vo.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession session;

	@Override
	public int securityInsert(MemberSecurity set) {
		// TODO Auto-generated method stub
		return session.insert("securityInsert", set);
	}

	@Override
	public MemberSecurity securitySelect(String userId) {
		// TODO Auto-generated method stub
		return (MemberSecurity) session.selectOne("securitySelect", userId);
	}

	@Override
	public int securityDelete(String userId) {
		// TODO Auto-generated method stub
		return session.delete("securityDelete", userId);
	}

	@Override
	public MemberVO memberSelect(String userId) {
		// TODO Auto-generated method stub
		return (MemberVO) session.selectOne("memberSelect", userId);
	}

	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		return session.insert("memberInsert");

	}

	@Override
	public int memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		return session.update("memberUpdate", mvo);
	}

	@Override
	public int memberDelete(String userId) {
		// TODO Auto-generated method stub
		return session.delete("memberDelete", userId);
	}

}
