package com.hao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="request_type_2")
public class Request_type_2 {
	@Id
	private int request_type_2_id;
	private String type_name;
	private int request_type_1_id;
	public int getRequest_type_2_id() {
		return request_type_2_id;
	}
	public void setRequest_type_2_id(int request_type_2_id) {
		this.request_type_2_id = request_type_2_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getRequest_type_1_id() {
		return request_type_1_id;
	}
	public void setRequest_type_1_id(int request_type_1_id) {
		this.request_type_1_id = request_type_1_id;
	}
	
}
