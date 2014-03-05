package com.tepusoft.dto;

import java.util.ArrayList;
import java.util.List;

public class DeptModel {
	private String id;
	private String text;
	private List<DeptModel> children = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<DeptModel> getChildren() {
		return children;
	}
	public void setChildren(List<DeptModel> children) {
		this.children = children;
	}
	
	
	
}
