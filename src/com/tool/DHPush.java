package com.tool;

import java.util.Map;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class DHPush {
	//极光推送>>Android
	   //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
	   public static void jpushAndroid(Map<String, String> parm) {
	       // 设置好账号的app_key和masterSecret 
	       String appKey = "**************";
	       String masterSecret = "************";
	       //创建JPushClient
	       JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	       //推送的关键,构造一个payload 
	       PushPayload payload = PushPayload.newBuilder()
	            .setPlatform(Platform.android())//指定android平台的用户
	            .setAudience(Audience.all())//你项目中的所有用户
	            .setNotification(Notification.android(parm.get("msg"), "这是title", parm))
	            //发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
	            .setOptions(Options.newBuilder().setApnsProduction(false).build())
	            //这里是指定开发环境,不用设置也没关系
	            .setMessage(Message.content(parm.get("msg")))//自定义信息
	            .build();
	       try {
	            PushResult pu = jpushClient.sendPush(payload);

	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        }    
	   }
	 //极光推送>>ios
	   //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
	   public static  void jpushIOS(Map<String, String> parm) {
	       // 设置好账号的app_key和masterSecret是必须的
	       String appKey = "*********************";
	       String masterSecret = "**********************";

	       //创建JPushClient
	       JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	       PushPayload payload = PushPayload.newBuilder()
	            .setPlatform(Platform.ios())//ios平台的用户
	            .setAudience(Audience.all())//所有用户
	            .setNotification(Notification.newBuilder()
	            .addPlatformNotification(IosNotification.newBuilder()
	                            .setAlert(parm.get("msg"))
	                            .setBadge(+1)
	                            .setSound("happy")
	                            .addExtras(parm)
	                            .build())
	                    .build())
	            .setOptions(Options.newBuilder().setApnsProduction(false).build())
	            .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
	            .build();

	       try {
	            PushResult pu = jpushClient.sendPush(payload);

	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        }    
	   }
	   
	   
}
