package com.tepusoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tepusoft.entity.DataDictionary;
@Entity
@Table(name="wfu_major")
public class Major implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8502903387968430262L;
	private String majorId;
	private String majorName;
	private DataDictionary academy;
	private Date createTime;
	
	@Id
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
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
