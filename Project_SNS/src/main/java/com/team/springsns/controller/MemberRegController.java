package com.team.springsns.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team.springsns.model.MemberInfo;
import com.team.springsns.service.MemberRegService;

@Controller
@RequestMapping("/team/memberReg")
public class MemberRegController {

	@Autowired
	private MemberRegService regService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getLoginForm() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("team/memberRegForm");
		//              /WEB-INF/                      .jsp

		System.out.println("컨트롤러 get방식");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView memberReg(MemberInfo memberInfo, HttpServletRequest request) {
		System.out.println("컨트롤러 post방식");
		// 에러의 경우 이동경로 설정이 가능
		ModelAndView modelAndView = new ModelAndView();
		// 정상처리 된 경우 mypage로 이동
		modelAndView.setViewName("team/team");

		try {
			int resultCnt = regService.memberReg(memberInfo, request);  // memberInfo 정보를 서비스에 보냄
			// resultCnt==0인 경우도 생김 (오류) >> 정상처리 되지 않음
			// resultCnt에 따라 처리되는 결과를 나눌 수 있음
									
			if (resultCnt < 1) {
				modelAndView.setViewName("team/regFail");
			} else {
				modelAndView.setViewName("team/team");
			}

		} catch (SQLException e) {
			// 예외 발생 시
			modelAndView.setViewName("team/regFail");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("컨트롤러 post방식 끝");
		return modelAndView;
	}
}
