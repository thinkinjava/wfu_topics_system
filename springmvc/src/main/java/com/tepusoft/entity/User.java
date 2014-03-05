package com.tepusoft.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = -1451239211868263389L;
	private String userId;
	private String userName;
	private String passWord;
	private String userType;  //0部门	,1教师,2学生,3访客
	private String personName;
	
	private Set<UsersRoles> userRoles = new HashSet<UsersRoles>(0);
	
	@Id
	@Column(name = "userId", nullable = false, length = 36)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="users")
	public Set<UsersRoles> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UsersRoles> userRoles) {
		this.userRoles = userRoles;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}

}
