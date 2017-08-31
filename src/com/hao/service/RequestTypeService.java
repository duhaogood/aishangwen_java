package com.hao.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.dao.BaseDao;
import com.hao.entity.Request_type_1;
import com.hao.entity.Request_type_2;
import com.tool.model.ReturnModel;

@Service(value = "requestTypeService")
public class RequestTypeService {
	@Autowired
	private BaseDao dao;
	/**
	 * 获取一级问题分类信息
	 */
	public ReturnModel getAllRequestType_1(HttpServletRequest request,
			HttpServletResponse response){
		ReturnModel model = new ReturnModel();
		@SuppressWarnings("unchecked")
		List<Request_type_1> allEntity = (List<Request_type_1>) dao.getAllEntity("from Request_type_1");
		model.setMsg("获取一级分类成功");
		model.setValue(allEntity);
		model.setSuccess(true);
		return model;
	}
	/**
	 * 获取二级分类信息
	 * @param request--一级分类id必传：request_type_1_id
	 */
	public ReturnModel getAllRequestType_2(HttpServletRequest request,
			HttpServletResponse response){
		ReturnModel model = new ReturnModel();
		String request_type_1_id = request.getParameter("request_type_1_id");
		if (request_type_1_id == null || request_type_1_id.length() == 0) {
			model.setMsg("一级分类id必传");
			model.setValue("");
			model.setSuccess(false);
		}else{
			@SuppressWarnings("unchecked")
			List<Request_type_2> list = (List<Request_type_2>)dao.getAllEntity("from Request_type_2 where request_type_1_id="+request_type_1_id);
			model.setMsg("获取二级分类成功");
			model.setValue(list);
			model.setSuccess(true);
		}
		return model;
	}
	
}
