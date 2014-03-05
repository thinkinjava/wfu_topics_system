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
@Table(name="wfu_teacherInfo")
public class TeacherInfo implements Serializable{
	private static final long serialVersionUID = 7430043502584571727L;
	private String teaId;
	private String userName;
	private String teaName;
	private String teaIdCard;
	private String teaEmail;
	private String teaPhone;
	private DataDictionary teaTitle;
	private Troom teaTroom;
	private DataDictionary teaAcademy;
	private Dept teaDept;
	private int teaAge;
	private String teaSex;
	private Date createTime;
	private String roleNames;
	private String address;
	private String remark;
	
	@Id
	public String getTeaId() {
		return teaId;
	}
	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaIdCard() {
		return teaIdCard;
	}
	public void setTeaIdCard(String teaIdCard) {
		this.teaIdCard = teaIdCard;
	}
	public String getTeaEmail() {
		return teaEmail;
	}
	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}
	public String getTeaPhone() {
		return teaPhone;
	}
	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teaTitle")
	public DataDictionary getTeaTitle() {
		return teaTitle;
	}
	public void setTeaTitle(DataDictionary teaTitle) {
		this.teaTitle = teaTitle;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teaTroom")
	public Troom getTeaTroom() {
		return teaTroom;
	}
	public void setTeaTroom(Troom teaTroom) {
		this.teaTroom = teaTroom;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teaAcademy")
	public DataDictionary getTeaAcademy() {
		return teaAcademy;
	}
	public void setTeaAcademy(DataDictionary teaAcademy) {
		this.teaAcademy = teaAcademy;
	}

	public String getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teaDept")
	public Dept getTeaDept() {
		return teaDept;
	}
	public void setTeaDept(Dept teaDept) {
		this.teaDept = teaDept;
	}
	public int getTeaAge() {
		return teaAge;
	}
	public void setTeaAge(int teaAge) {
		this.teaAge = teaAge;
	}
	
	
	
}
