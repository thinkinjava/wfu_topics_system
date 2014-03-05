package com.tepusoft.shiro;

import java.io.Serializable;

public  class ShiroUser implements Serializable
{
	private static final long serialVersionUID = -1748602382963711884L;
	private String userId;
	private String userName;
	private String userType;
	private String personName;
	
	
	public ShiroUser(String userId, String userName,String userType,String personName)
	{
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType=userType;
		this.personName=personName;
		
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public String toString()
	{
		return userName;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	    
}
