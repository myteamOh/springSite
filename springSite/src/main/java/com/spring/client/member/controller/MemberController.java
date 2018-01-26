package com.spring.client.member.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.client.login.service.LoginService;
import com.spring.client.login.vo.LoginVO;
import com.spring.client.member.service.MemberService;
import com.spring.client.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	Logger logger = Logger.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private LoginService loginService;

	/*
	 * 회원가입폼
	 * 
	 */
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String joinForm(Model model) {
		logger.info("join.do get 방식에 의한 메서드 호출 성공");
		return "member/join";
	}

	/* 사용자 아이디 중복 체크 메서드 */
	@ResponseBody
	@RequestMapping(value = "/userIdConfirm.do", method = RequestMethod.POST)
	public String userIdConfirm(@RequestParam("userId") String userId) {
		int result = memberService.userIdConfirm(userId);
		return result + "";
	}

	/*
	 * 회원가입처리
	 */
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public ModelAndView memberInsert(@ModelAttribute MemberVO mvo) {
		logger.info("join.do post 방식에 의한 메서드 호출 성공");
		ModelAndView mav = new ModelAndView();

		int result = 0;
		result = memberService.memberInsert(mvo);

		switch (result) {
		case 1:
			mav.addObject("errCode"); // 사용자아이디가 이미 존재한다면
			mav.setViewName("member/join");
			break;
		case 3:
			mav.addObject("errCode", 3);
			mav.setViewName("member/join_success");
			// 새로운계정 추가성공 , 로그인페이지로이동
			break;
		default:
			mav.addObject("errCode", 2); // 새로운계정 추가 실패
			mav.setViewName("member/join");
			break;
		}
		return mav;
	}

	/*
	 * 수정
	 */
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public ModelAndView memberModify(HttpSession session) {
		logger.info("modify.do get 방식에 의한 메서드 호출 성공");
		ModelAndView mav = new ModelAndView();

		LoginVO login = (LoginVO) session.getAttribute("login");

		if (login == null) {
			mav.setViewName("member/login");
			return mav;
		}
		MemberVO vo = memberService.memberSelect(login.getUserId());
		mav.addObject("member", vo);
		mav.setViewName("member/modify");
		return mav;
	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public ModelAndView memberModifyProcess(@ModelAttribute("MemberVO") MemberVO mvo, HttpSession session) {
		logger.info("modify.do post 방식에 의한 메서드 호출 성공");
		ModelAndView mav = new ModelAndView();

		LoginVO login = (LoginVO) session.getAttribute("login");

		if (login == null) {
			mav.setViewName("member/login");
			return mav;
		}
		mvo.setUserId(login.getUserId());
		MemberVO vo = memberService.memberSelect(mvo.getUserId());
		if (loginService.loginSelect(mvo.getUserId(), mvo.getOldUserPw()) == null) {
			mav.addObject("errCode", 1);
			mav.addObject("member", vo);
			mav.setViewName("member/modify");
			return mav;
		}
		if (memberService.memberUpdate(mvo)) {
			mav.setViewName("redirect:/member/logout.do");
			return mav;
		} else {
			mav.addObject("errCode", 2);
			mav.addObject("member", vo);
			mav.setViewName("member/modify");
			return mav;
		}
	}

	@RequestMapping("/delete.do")
	public ModelAndView memberDelete(HttpSession session) {
		logger.info("delete.do get 방식에 의한 메서드 호출 성공");
		ModelAndView mav = new ModelAndView();
		LoginVO login = (LoginVO) session.getAttribute("login");

		if (login == null) {
			mav.setViewName("member/join");
			return mav;
		}
		int errCode = memberService.memberDelete(login.getUserId());
		switch (errCode) {
		case 2:
			mav.setViewName("redirect:/member/logout.do");
			break;

		case 3:
			mav.addObject("errCode", 3);
			mav.setViewName("member/login");
			break;
		}
		return mav;
	}
}
