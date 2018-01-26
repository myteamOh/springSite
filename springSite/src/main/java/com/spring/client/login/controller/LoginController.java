package com.spring.client.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.client.login.service.LoginService;
import com.spring.client.login.vo.LoginVO;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/member")
public class LoginController {
	Logger logger = Logger.getLogger("LoginController.class");

	@Autowired
	private LoginService loginService;

	/* 로그인 화면 보여주기 위한 메서드 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		logger.info("login.do get 호출성공 ");
		return "member/login";
	}

	/*
	 * 로그인 처리 메서드 참고 : 로그인 실패시 처리 내용 포함
	 * 
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginProc(@ModelAttribute("LoginVO") LoginVO lvo, HttpSession session,
			HttpServletRequest request) {
		logger.info("login.do post 호출 성공");
		ModelAndView mav = new ModelAndView();
		String userId = lvo.getUserId();
		int resultData = loginService.loginHistoryInsert(lvo);

		if (resultData == 1) {
			mav.addObject("errCode,1");
			mav.setViewName("member/login");
			return mav;
		} else {
			LoginVO vo = loginService.loginHistorySelect(userId);
			logger.info("최근 로그인 일시 :" + new SimpleDateFormat("YYYY-MM-dd").format(vo.getLastSuccessedLogin()));
			logger.info("retry:" + vo.getRetry());

			/* 로그인 시도횟수가 5회 넘으면 30초간 로그인금지 */
			if (vo.getRetry() >= 5) {
				if (new Date().getTime() - vo.getLastFailedLogin() < 30000) {
					mav.addObject("errCode", 6); // 30초동안 로그인 잠금 알림
					mav.setViewName("member/login");
					return mav;
				} else {
					vo.setRetry(0);
					vo.setLastFailedLogin(0);
					loginService.loginHistoryUpdate(vo);
				}
			}
			LoginVO loginCheckResult = loginService.loginSelect(lvo.getUserId(), lvo.getUserPw());

			// 로그인 틀리면 로그인 시도횟수를 1 증가시키고
			// 로그인 실패 시간을 DB에 업데이트한다.
			if (loginCheckResult == null) {
				vo.setRetry(vo.getRetry() + 1);
				vo.setLastFailedLogin(new Date().getTime());
				loginService.loginHistoryUpdate(vo);

				mav.addObject("retry", vo.getRetry());
				mav.addObject("errCode", 1);
				mav.setViewName("member/login");
				return mav;
			}

			// 로그인이 성공하면 로그인 시도횟수를 0으로 reset,
			// 마지막으로 로그인 실패 시간 0 으로 reset,
			// 성공한 클라이언트 IP를 DB로 업데니트,로그인 성공시간 DB에 업데이트
			else {
				vo.setRetry(0);
				vo.setLastFailedLogin(0);
				vo.setLastSuccessedLogin(new Date().getTime());
				vo.setClientIP(request.getRemoteAddr());
				loginService.loginHistoryUpdate(vo);

				session.setAttribute("login", loginCheckResult);
				System.out.println(session.getAttribute("login").toString());
				mav.setViewName("member/login");
				return mav;
			}

		}

	}

	/*
	 * 로그아웃 처리 메서드
	 * 
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		session = request.getSession(true);
		return "redirect:/member/login.do";
	}
}
