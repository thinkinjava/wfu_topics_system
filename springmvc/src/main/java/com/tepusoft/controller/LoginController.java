package com.tepusoft.controller;

import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tepusoft.dto.UsersDto;
import com.tepusoft.entity.Log;
import com.tepusoft.service.LogServiceI;
import com.tepusoft.shiro.CaptchaUsernamePasswordToken;
import com.tepusoft.shiro.IncorrectCaptchaException;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.IpUtil;
import com.tepusoft.utils.JsonData;
/**
 * 功能用于登录的Controller
 * @author "Lijinzhao"
 *time:2013-11-10
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController{
	
	@Autowired
	private LogServiceI logServiceI;
	
	/**
	 * 功能：用于登录验证（shiro）并返回json数据
	 * @param usersDto
	 * @return jsonData
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonData login(UsersDto usersDto,HttpServletRequest request){
		JsonData jsonData = new JsonData();
		Subject subject=SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token=new CaptchaUsernamePasswordToken();
		if(usersDto.getUserName()!=null&&usersDto.getPassWord()!=null){
	        token.setUsername(usersDto.getUserName());
	        token.setPassword(usersDto.getPassWord().toCharArray());
	        token.setUserType(usersDto.getUserType());
		}

		token.setCaptcha(usersDto.getCaptcha());
        //token.setRememberMe(true);
        try{
        	doCaptchaValidate(token,request);
            subject.login(token);
            String ip=IpUtil.getIpAddr(request);
            //登录成功写入登录日志,记录人员登录时间及ip
            Log log=new Log();
            log.setIp(ip);
            log.setLogId(UUID.randomUUID().toString());
            log.setLogDate(new Date());
            log.setType(0);
            log.setUserName(usersDto.getUserName());
            logServiceI.saveLog(log);
            jsonData.setStatus("true");
            jsonData.setMessage("登陆成功");
        }
        catch (UnknownSessionException use) {
            subject = new Subject.Builder().buildSubject();
            subject.login(token);
            jsonData.setStatus("false");
            jsonData.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
        }
        catch(UnknownAccountException ex){
            jsonData.setStatus("false");
            jsonData.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		}
        catch (IncorrectCredentialsException ice) {
        	 jsonData.setStatus("false");
             jsonData.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
        } 
        catch (LockedAccountException lae) {
        	 jsonData.setStatus("false");
             jsonData.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
        }
        catch (IncorrectCaptchaException e) {
	       	 jsonData.setStatus("false");
	         jsonData.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		}
        catch (AuthenticationException ae) { 
        	jsonData.setStatus("false");
            jsonData.setMessage(Constants.AUTHENTICATION_EXCEPTION);
        } 
        catch(Exception e){
        	jsonData.setStatus("false");
            jsonData.setMessage(Constants.UNKNOWN_EXCEPTION);
        }
         token.clear();
		return jsonData;
	}
	/**
	 * 功能注销登录
	 * @author "Lijinzhao"
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout() throws Exception
	{
		SecurityUtils.getSubject().logout();
		return "注销成功";
	}
	//验证码校验
	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token,HttpServletRequest request)
	{
		
		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null &&!captcha.equalsIgnoreCase(token.getCaptcha()))
		{
			throw new IncorrectCaptchaException("验证码错误！");
		}
		return true;
	}
}
