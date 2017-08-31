package com.hao.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.hao.dao.BaseDao;
import com.hao.entity.UserEntity;
import com.tool.DHTool;
import com.tool.StringConfigure;
import com.tool.model.ReturnModel;

@Service(value = "userService")
public class UserService {
	@Autowired
	private BaseDao dao;

	/**
	 * 登录服务
	 * 
	 * @param request
	 *            --必传字段：user_name(用户名),user_pass(用户密码)
	 * @return 返回给用户的信息
	 */
	public ReturnModel login(HttpServletRequest request) {
		String user_name = request.getParameter("user_name");
		String user_pass = request.getParameter("user_pass");
		ReturnModel model = new ReturnModel();
		model.setValue("");
		model.setSuccess(false);
		if (user_name == null) {
			model.setMsg("名字不能为空");
			return model;
		}
		if (user_pass == null) {
			model.setMsg("密码必传");
			return model;
		}
		UserEntity user = (UserEntity) dao
				.getEntity("from UserEntity where user_name='" + user_name
						+ "'");
		if (user == null) {
			model.setMsg("用户名不存在");
			return model;
		}
		if (user_pass.equals(user.getUser_pass())) {
			model.setSuccess(true);
			model.setValue(user);
			model.setMsg("登录成功");
			DHTool.getInfoLog().info(user_name + "--登录成功");
			
			
			JPushClient jpushClient = 
					new JPushClient(StringConfigure.JG_MASTER_SECRET, 
							StringConfigure.JG_APP_KEY, null, 
							ClientConfig.getInstance());
			
		    // For push, all you need do is to build PushPayload object.
		    PushPayload payload = buildPushObject_all_all_alert();

		    try {
		        PushResult result = jpushClient.sendPush(payload);

		    } catch (APIConnectionException e) {
		        // Connection error, should retry later

		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		    	
		    }
			
			
			return model;
		}
		model.setMsg("密码错误");
		return model;
	}

	private PushPayload buildPushObject_all_all_alert() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 注册服务
	 * 
	 * @param request
	 *            --必传字段：user_name(用户名),user_pass(用户密码)
	 * @return 返回给用户的信息
	 */
	// public ReturnModel register(HttpServletRequest request){
	// String user_name = request.getParameter("user_name");
	// String user_pass = request.getParameter("user_pass");
	// ReturnModel model = new ReturnModel();
	// model.setValue("");
	// model.setSuccess(false);
	// if (user_name == null) {
	// model.setMsg("名字不能为空");
	// return model;
	// }
	// if (user_pass == null) {
	// model.setMsg("密码必传");
	// return model;
	// }
	// //如果用户已经存在，告诉用户不能注册
	// UserEntity user = (UserEntity)
	// dao.getEntity("from UserEntity where user_name='" + user_name + "'");
	// if (user != null) {
	// model.setMsg("用户已经存在");
	// return model;
	// }
	// System.out.println("user_name:" + user_name + ",user_pass:" + user_pass);
	// //插入数据库
	// UserEntity user_new = new UserEntity();
	// user_new.setUser_name(user_name);
	// user_new.setUser_pass(user_pass);
	// boolean flag = userDao.addUserEntity(user_new);
	//
	// if (flag == true) {
	// model.setSuccess(true);
	// model.setMsg("注册成功");
	// DHTool.getInfoLog().info("用户[" + user_name + "]注册成功");
	// return model;
	// }
	// model.setMsg("注册失败");
	// return model;
	// }

	public ReturnModel getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		ReturnModel model = new ReturnModel();
		@SuppressWarnings("unchecked")
		List<UserEntity> users = (List<UserEntity>) dao
				.getAllEntity("from UserEntity");
		model.setMsg("获取成功");
		model.setSuccess(true);
		model.setValue(users);
		return model;
	}

}
