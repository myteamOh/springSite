package com.spring.client.member.vo;

public class MemberSecurity {
	private String userId;
	private String salt;

	public MemberSecurity() {
		super();
	}

	public MemberSecurity(String userId, String salt) {
		super();
		this.userId = userId;
		this.salt = salt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "MemberSecurity [userId=" + userId + ", salt=" + salt + "]";
	}

}
