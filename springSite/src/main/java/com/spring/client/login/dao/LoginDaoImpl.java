package com.spring.client.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.client.login.vo.LoginVO;

public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession session;

	@Override
	public LoginVO userIdSelect(String userId) {
		// TODO Auto-generated method stub
		return (LoginVO) session.selectOne("userIdSelect", userId);
	}

	@Override
	public LoginVO loginSelect(LoginVO lvo) {
		// TODO Auto-generated method stub
		return (LoginVO) session.selectOne("loginSelect", lvo);
	}

	@Override
	public int loginHistoryInsert(LoginVO lvo) {
		// TODO Auto-generated method stub
		return session.insert("loginHistory", lvo);
	}

	@Override
	public int loginHistoryUpdate(LoginVO lvo) {
		// TODO Auto-generated method stub
		return session.update("loginHistory", lvo);
	}

	@Override
	public LoginVO loginHistorySelect(String userId) {
		// TODO Auto-generated method stub
		return (LoginVO) session.selectOne("loginHistorySelect", userId);
	}

}
