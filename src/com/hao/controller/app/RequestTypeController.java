package com.hao.controller.app;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.service.RequestTypeService;
import com.tool.DHTool;
import com.tool.model.ReturnModel;
/**
 * 问题分类相关接口
 * @author Hao
 *
 */
@Controller
@RequestMapping("/request_type")
public class RequestTypeController {
	@Autowired
	private RequestTypeService requestTypeService;
	/**
	 * 获取一级问题分类信息
	 */
	@RequestMapping(value = "/getType_1.app")
	private void getAllType_1(HttpServletRequest request,
			HttpServletResponse response){
		ReturnModel model = requestTypeService.getAllRequestType_1(request, response);
		DHTool.writeToResponse(model, response);
	}
	/**
	 * 获取二级分类信息
	 * @param request--一级分类id必传：request_type_1_id
	 * @param response
	 */
	@RequestMapping(value = "/getType_2.app")
	private void getAllType_2(HttpServletRequest request,
			HttpServletResponse response){
		ReturnModel model = requestTypeService.getAllRequestType_2(request, response);
		DHTool.writeToResponse(model, response);
	}
	
}
