package com.spring.client.login.vo;

/*	로그인 시도횟수 제한시 고려해야 할 항목들을 정리해 보면,
	1. 로그인 시도 회수
	로그인 시도시 마다 1씩 증가 시켜 , 시도 횟수가 5보다 큰 경우 일정 시간 동안
	로인근 기능이 잠금 상태가 되도록 한다
	2.마지막으로 실패한 로그인 시간정보
	이 시간 정보를 이용하여 로그인 잠금 상태가 유지 되어야 하는 시간을 설정 할 수 있다
	3.마지막으로 성공한 로그인 시간 정보
	  이 시간 정보를 이용하여 마지막으로 로그인한 시간 정보를 사용자에게 알려줌으로써
	  자기 계정이 혹시 다른 사람에 의해 사용되었는지 여부를 사용자가 점검 할 수 있도록 한다.
	4.로그인한 사용자의 IP 정보
*/

public class LoginHistory {

	private int idx;
	private int retry;
	private long lastFailedLogin;
	private long lastSuccessedLogin;
	private String clientIP;

	public LoginHistory() {
	}

	public LoginHistory(int idx, int retry, long lastFailedLogin, long lastSuccessedLogin, String clientIP) {
		super();
		this.idx = idx;
		this.retry = retry;
		this.lastFailedLogin = lastFailedLogin;
		this.lastSuccessedLogin = lastSuccessedLogin;
		this.clientIP = clientIP;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public long getLastFailedLogin() {
		return lastFailedLogin;
	}

	public void setLastFailedLogin(long lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}

	public long getLastSuccessedLogin() {
		return lastSuccessedLogin;
	}

	public void setLastSuccessedLogin(long lastSuccessedLogin) {
		this.lastSuccessedLogin = lastSuccessedLogin;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

}
