package com.spring.client.member.vo;

import java.sql.Timestamp;

import com.spring.client.login.vo.LoginVO;

public class MemberVO extends LoginVO {

	private String oldUserPw;
	private String pinno;
	private String email;
	private String phone;
	private Timestamp joinData;

	public String getOldUserPw() {
		return oldUserPw;
	}

	public void setOldUserPw(String oldUserPw) {
		this.oldUserPw = oldUserPw;
	}

	public String getPinno() {
		return pinno;
	}

	public void setPinno(String pinno) {
		this.pinno = pinno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getJoinData() {
		return joinData;
	}

	public void setJoinData(Timestamp joinData) {
		this.joinData = joinData;
	}

	@Override
	public String toString() {
		return "MemberVO [oldUserPw=" + oldUserPw + ", pinno=" + pinno + ", email=" + email + ", phone=" + phone
				+ ", joinData=" + joinData + "]";
	}

}
