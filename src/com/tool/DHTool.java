package com.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.hao.dao.BaseDao;
import com.tool.model.ReturnModel;

public class DHTool {

	//log目录地址
	public static String LOG_PATH = "C:/log/mhxy/";
	public static Logger log_info = Logger.getLogger(DHTool.class);
	public static Logger log_error = Logger.getLogger(DHTool.class);
	public static MyAppender appender_info = (MyAppender)LogManager.getLoggerRepository().getRootLogger().getAppender("info");
	public static Appender appender_error = LogManager.getLoggerRepository().getRootLogger().getAppender("error");
	public static int count = 0;
	/**
	 * 获取打印info的log
	 * @return info专用Logger对象
	 */
	public static Logger getInfoLog(){
		
		if(appender_info instanceof FileAppender) { 
			 FileAppender fileAppender = (FileAppender)appender_info; 
			 String temp_str="";  
			 Date dt = new Date();   
			 //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			 temp_str=sdf.format(dt);  
			 String name = fileAppender.getFile();
			 String name_new = LOG_PATH + temp_str;
			 String name_info = name_new + "_info.log";
			 if (!name.equals(name_info)) {
				 fileAppender.setFile(name_info);
				 fileAppender.activateOptions(); 
			}
			 return log_info;
		}
		return null;
	}
	/**
	 * 获取打印错误的log
	 * @return error专用Logger对象
	 */
	public static Logger getErrorLog(){
		if(appender_error instanceof FileAppender) { 
			 FileAppender fileAppender = (FileAppender)appender_error; 
			 String temp_str="";  
			 Date dt = new Date();   
			 //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			 temp_str=sdf.format(dt);  
			 String name = fileAppender.getFile();
			 String name_new = LOG_PATH + temp_str;
			 String name_info = name_new + "_error.log";
			 if (!name.equals(name_info)) {
				 fileAppender.setFile(name_info);
				 fileAppender.activateOptions(); 
			}
			 return log_error;
		}
		return null;
	}
	private DHTool() {
	}
	//私有变量，外部不可见
	private static DHTool instance = new DHTool();

	/**
	 * 获取工具实例
	 * @return DHTool实例
	 */
	public static DHTool getDHTool() {
		return instance;
	}
	/**
	 * 获取由 Map<String, String> 转化的json字符串
	 * @param map
	 * @return
	 */
	public static String getJsonStringFromMap(Map<String, String> map){
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	/**
	 * 获取BaseDao 对象，操作数据库
	 * @return
	 */
	public static BaseDao getBaseDao(){
		WebApplicationContext wc = ResourceConext.getApplicationContext();
		BaseDao baseDao = (BaseDao)wc.getBean("baseDao");
		return baseDao;
	}
	
	/**
	 * 把model的所有信息以json的格式写入response
	 * 
	 * @param model ReturnModel对象
	 * @param response Controller传入的参数
	 */
	public static void writeToResponse(ReturnModel model, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = JSONObject.fromObject(model);
		PrintWriter out = null;
		String s = json.toString();
		try {
			out = response.getWriter();
			out.append(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 获取JSONObject中的message_type
	 */
	public static Map<String, String> getMessage_type(JSONObject json){
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("rawtypes")
		Iterator it = json.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next()); 
	           String value = (String) json.get(key);
	           map.put(key, value);
	       }  
		return map;
	}
}
