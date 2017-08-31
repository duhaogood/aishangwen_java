package com.hao.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hao.service.UserService;
import com.tool.model.ReturnModel;

@Controller
public class UserWEBController {
	@Autowired
	private UserService userService;
	
	//登录接口
	@RequestMapping(value = "/login.do")
	public ModelAndView log_web(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		ReturnModel model = userService.getAllUsers(request, response);
		mv.addObject("msg", "所有用户信息");
		mv.setViewName("hello");
		mv.addObject("value", model.getValue());
		return mv;
	}
	
	
	
	
}
