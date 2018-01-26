package com.spring.client.member.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.member.dao.MemberDao;
import com.spring.client.member.vo.MemberSecurity;
import com.spring.client.member.vo.MemberVO;
import com.spring.common.util.OpenCrypt;
import com.spring.common.util.Util;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	Logger logger = Logger.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberDao memberDao;

	@Override
	public int userIdConfirm(String userId) {
		// TODO Auto-generated method stub
		int result;
		if (memberDao.memberSelect(userId) != null) {
			result = 1;
		} else {
			result = 2;
		}
		return result;
	}

	@Override
	public int memberInsert(MemberVO mvo) {
		// TODO Auto-generated method stub
		int sCode = 2;
		if (memberDao.memberSelect(mvo.getUserId()) != null) {
			return 1;
		} else {
			try {
				MemberSecurity sec = new MemberSecurity();
				sec.setUserId(mvo.getUserId());
				sec.setSalt(Util.getRandomString());
				sCode = memberDao.securityInsert(sec);

				if (sCode == 1) {
					mvo.setUserPw(new String(OpenCrypt.getSHA256(mvo.getUserPw(), sec.getSalt())));
					memberDao.memberInsert(mvo);
					return 3;
				} else {
					return 2;
				}

			} catch (RuntimeException e) {
				// TODO: handle exception
				e.printStackTrace();
				return 2;
			}
		}

	}

	public MemberVO memberSelect(String userId) {
		MemberVO vo = memberDao.memberSelect(userId);
		return vo;
	}

	@Override
	public boolean memberUpdate(MemberVO mvo) {
		// TODO Auto-generated method stub
		try {
			if (!mvo.getUserPw().isEmpty()) {
				MemberSecurity sec = memberDao.securitySelect(mvo.getUserId());
				mvo.setUserPw(new String(OpenCrypt.getSHA256(mvo.getUserPw(), sec.getSalt())));
			}
			memberDao.memberUpdate(mvo);
		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int memberDelete(String userId) {
		// TODO Auto-generated method stub
		int mCode, sCode, isSucessCode = 3;
		try {
			mCode = memberDao.memberDelete(userId);
			if (mCode == 1) {
				sCode = memberDao.securityDelete(userId);
				if (sCode == 1) {
					isSucessCode = 2;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isSucessCode = 3;
		}
		return isSucessCode;
	}

}
