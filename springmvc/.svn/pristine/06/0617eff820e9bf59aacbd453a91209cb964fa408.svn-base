package com.topic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.TeacherInfo;

@Entity
@Table(name="wfu_topic")
public class Topic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478591970588533275L;
	private String topicId;
	private String topicName;
	private TeacherInfo teacherInfo;
	private DataDictionary topType;
	private DataDictionary academy;
	private String topicMajorIds;
	private String topicMajorNames;
	private Date topicCreateTime;
	private String isSelect;   //0为未选（默认），1为已选，2为不可见
	private String teacherName;//教师姓名冗余字段
	
	@Id
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicMajorIds() {
		return topicMajorIds;
	}
	public void setTopicMajorIds(String topicMajorIds) {
		this.topicMajorIds = topicMajorIds;
	}
	public String getTopicMajorNames() {
		return topicMajorNames;
	}
	public void setTopicMajorNames(String topicMajorNames) {
		this.topicMajorNames = topicMajorNames;
	}
	public Date getTopicCreateTime() {
		return topicCreateTime;
	}
	public void setTopicCreateTime(Date topicCreateTime) {
		this.topicCreateTime = topicCreateTime;
	}
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topicType")
	public DataDictionary getTopType() {
		return topType;
	}
	public void setTopType(DataDictionary topType) {
		this.topType = topType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topicAcademyId")
	public DataDictionary getAcademy() {
		return academy;
	}
	public void setAcademy(DataDictionary academy) {
		this.academy = academy;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="topicPersonId")
	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	


	
	
}
