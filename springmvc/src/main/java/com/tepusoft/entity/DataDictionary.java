package com.tepusoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="datadictionary")
public class DataDictionary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9014753703872826885L;
	private String dicId;
	private String dicName;
	private String dicPid;
	private String dicMark;
	private String dicDesc;
	
	@Id
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicPid() {
		return dicPid;
	}
	public void setDicPid(String dicPid) {
		this.dicPid = dicPid;
	}
	public String getDicMark() {
		return dicMark;
	}
	public void setDicMark(String dicMark) {
		this.dicMark = dicMark;
	}
	public String getDicDesc() {
		return dicDesc;
	}
	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}
	
	
}
