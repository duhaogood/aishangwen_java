package com.hao.controller.app;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tool.DHTool;
import com.tool.model.ReturnModel;

@Controller
public class UploadAPPController {
	//移动端上传文件接口
	@RequestMapping(value = "/upload.app" , method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file , HttpServletResponse response , HttpServletRequest request) throws Exception{
		ReturnModel model = new ReturnModel();
		model.setSuccess(false);
		model.setValue("");
		if (file.getSize() == 0) {
			DHTool.writeToResponse(model, response);
			return;
		}
		String fileName = file.getOriginalFilename();
		File ff = new File("D:\\java\\apache-tomcat-8.0\\webapps\\image\\asw\\" + System.currentTimeMillis() + "-" + fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), ff);
		model.setSuccess(true);
		model.setMsg("上传成功");
		DHTool.writeToResponse(model, response);
	}
	//post测试接口
	@RequestMapping(value = "/post.app" , method = RequestMethod.GET)
	public void posttest(HttpServletRequest request , HttpServletResponse response){
		System.out.println("进来POSTTEST");
		System.out.println("接收参数:" + request.getParameter("a"));
		ReturnModel model = new ReturnModel();
		model.setSuccess(true);
		model.setMsg("post成功");
		DHTool.writeToResponse(model, response);
	}
	
	
}
