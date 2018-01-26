package com.spring.client.login.dao;

import com.spring.client.login.vo.LoginVO;

public interface LoginDao {
	public LoginVO userIdSelect(String userId);

	public LoginVO loginSelect(LoginVO lvo);

	public int loginHistoryInsert(LoginVO lvo);

	public int loginHistoryUpdate(LoginVO lvo);

	public LoginVO loginHistorySelect(String userId);

}
