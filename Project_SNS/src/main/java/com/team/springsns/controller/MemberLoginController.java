package com.team.springsns.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.springsns.service.MemberLoginService;

@Controller
public class MemberLoginController {

	@Autowired
	private MemberLoginService service;
	
	// 로그인 처리하기
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView loginProcess(
			@RequestParam(value="userId", required=false) String userId, 
			@RequestParam(value="userPassword", required=true) String userPassword,
			HttpSession session ) throws SQLException {
			
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("team/loginFail"); // 실패하는걸 디폴트로 하면 if문에서 else 안써도 됨		
		
		if(userId != null && userPassword != null) {
			
			if(service.login(userId, userPassword, session)) {
				
				//정상적으로 들어왔을때
				modelAndView.setViewName("team/team");
			}			
		} 		
		return modelAndView;		
	}
}
