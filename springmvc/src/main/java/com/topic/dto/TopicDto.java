package com.topic.dto;

import java.util.Date;


public class TopicDto {
	private String topicId;
	private String topicName;
	private String topicPersonId;
	private String topicPersonName;
	private String topicPersonTitle;  //发布课题教师的职称
	private String topicType;
	private String topicMajorIds;
	private String topicMajorNames;
	private Date topicCreateTime;
	private String isSelect;   //0为未选（默认），1为已选，2为不可见
	private String teacherPhone;
	private String teacherEmail;
	private String teacherTitle;//教师职称 
	
	
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
	public String getTopicPersonId() {
		return topicPersonId;
	}
	public void setTopicPersonId(String topicPersonId) {
		this.topicPersonId = topicPersonId;
	}
	public String getTopicType() {
		return topicType;
	}
	public void setTopicType(String topicType) {
		this.topicType = topicType;
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
	public String getTopicPersonName() {
		return topicPersonName;
	}
	public void setTopicPersonName(String topicPersonName) {
		this.topicPersonName = topicPersonName;
	}
	public String getTopicPersonTitle() {
		return topicPersonTitle;
	}
	public void setTopicPersonTitle(String topicPersonTitle) {
		this.topicPersonTitle = topicPersonTitle;
	}
	public String getTeacherPhone() {
		return teacherPhone;
	}
	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getTeacherTitle() {
		return teacherTitle;
	}
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	 
	
	
	
	
}
