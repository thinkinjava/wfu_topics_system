package com.tepusoft.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tepusoft.entity.DataDictionary;
@Entity
@Table(name="wfu_troom")
public class Troom {
	private String troomId;
	private String troomName;
	private DataDictionary academy;
	private Date createTime;
	
	@Id
	public String getTroomId() {
		return troomId;
	}
	public void setTroomId(String troomId) {
		this.troomId = troomId;
	}
	public String getTroomName() {
		return troomName;
	}
	public void setTroomName(String troomName) {
		this.troomName = troomName;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="academy")
	public DataDictionary getAcademy() {
		return academy;
	}
	public void setAcademy(DataDictionary academy) {
		this.academy = academy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
