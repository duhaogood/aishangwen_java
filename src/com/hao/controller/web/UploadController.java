package com.hao.controller.web;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	//上传文件的jsp入口
	@RequestMapping(value = "/upload.do")
	public String showUploadPage(){
		return "upload";
		
	}
	
	@RequestMapping(value = "/uploadFile.do" , method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file")MultipartFile file) throws Exception{
		System.out.println("进来了");
		System.out.println("文件大小:" + file.getSize());
		if (file.getSize() == 0) {
			return "false";
		}
		String fileName = file.getOriginalFilename();
		System.out.println("本地文件名字:" + fileName);

		File ff = new File("D:\\uploadFiles\\" + System.currentTimeMillis() + "-" + fileName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), ff);
		
		/**
		 * 640-960
		 * 320-480
		 */
		
		return "success";
	}
	
	
}
