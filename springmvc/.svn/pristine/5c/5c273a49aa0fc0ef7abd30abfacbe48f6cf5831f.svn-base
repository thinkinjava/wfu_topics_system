package com.tepusoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="wfu_studentInfo")
public class StudentInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3090856864658128302L;
	private String stutId;
	private String userName;
	private String stuName;
	private String stuIdCard;
	private String stuEmail;
	private String stuPhone;
	private Major stuMajor;
	private DataDictionary stuAcademy;
	private String stuClass;  //班级
	private String stuGrade;  //年级
	private int stuAge;
	private String stuSex;
	private Date createTime;
	private String roleNames;
	private String address;
	private String remark;
	private String passWord;
	private String stuNation;//民族
	private String stuSubject;//本专科
	private String StuPoliticalLandscape;//政治面貌
	private String stuPost;//职务
	
	@Id
	public String getStutId() {
		return stutId;
	}
	public void setStutId(String stutId) {
		this.stutId = stutId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuIdCard() {
		return stuIdCard;
	}
	public void setStuIdCard(String stuIdCard) {
		this.stuIdCard = stuIdCard;
	}
	public String getStuEmail() {
		return stuEmail;
	}
	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}
	public String getStuPhone() {
		return stuPhone;
	}
	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stuMajor")
	public Major getStuMajor() {
		return stuMajor;
	}
	public void setStuMajor(Major stuMajor) {
		this.stuMajor = stuMajor;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stuAcademy")
	public DataDictionary getStuAcademy() {
		return stuAcademy;
	}
	public void setStuAcademy(DataDictionary stuAcademy) {
		this.stuAcademy = stuAcademy;
	}
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	public String getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(String stuGrade) {
		this.stuGrade = stuGrade;
	}
	
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getStuNation() {
		return stuNation;
	}
	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}
	public String getStuSubject() {
		return stuSubject;
	}
	public void setStuSubject(String stuSubject) {
		this.stuSubject = stuSubject;
	}
	public String getStuPoliticalLandscape() {
		return StuPoliticalLandscape;
	}
	public void setStuPoliticalLandscape(String stuPoliticalLandscape) {
		StuPoliticalLandscape = stuPoliticalLandscape;
	}
	public String getStuPost() {
		return stuPost;
	}
	public void setStuPost(String stuPost) {
		this.stuPost = stuPost;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	
}
