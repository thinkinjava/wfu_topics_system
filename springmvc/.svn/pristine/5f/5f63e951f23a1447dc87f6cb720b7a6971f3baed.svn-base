package com.tepusoft.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
* 类功能说明 TODO:密码以及验证码Token
* @author Lijinzhao
* 类修改者
*/

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken
{
	private static final long serialVersionUID = -3217596468830869181L;
	private String captcha;
	private String userType;

	public String getCaptcha()
	{
		return captcha;
	}

	public void setCaptcha(String captcha )
	{
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe,
			String host,String captcha)
	{
		super(username, password, rememberMe, host);
		this.captcha = captcha; 
	}

	public CaptchaUsernamePasswordToken()
	{
		super();
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
