package com.hao.messageModel;

public class Message_login_model {
	private String message_type;
	private String user_name;
	private String user_pass;
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	@Override
	public String toString() {
		return "Message_login_model [message_type=" + message_type
				+ ", user_name=" + user_name + ", user_pass=" + user_pass + "]";
	}
	
	
}
