package com.hao.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.service.UserService;
import com.tool.DHTool;
import com.tool.model.ReturnModel;

@Controller
@RequestMapping("/user")
public class UserAPPController {
	@Autowired
	private UserService userService;
	//登录接口
	@RequestMapping(value = "/login.app")
	public void log_app(HttpServletRequest request,HttpServletResponse response){
		ReturnModel model = userService.login(request);
		DHTool.writeToResponse(model, response);
	}
	//注册接口
	@RequestMapping(value = "/register.app")
	public void register(HttpServletRequest request,HttpServletResponse response){

	}
	
	
}
