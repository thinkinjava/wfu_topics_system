package com.tepusoft.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.tepusoft.shiro.ShiroUser;



/**
 * 基础控制器
 * 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * @author Lijinzhao
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}
	
	/**
	 * json格式的数据传输
	 * @author Lijinzhao
	 * time:2013-11-01
	 */
	public void jsonOut(Object object,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = response.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	public boolean isTheEmpty(Object o){
		if(o==null||o.equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 功能：得到当前登录用户的Id
	 * @author Lijinzhao
	 * @time 2013-12-17
	 * @return
	 */
	public String getOwnId(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		return userId;	
	}
	/**
	 * 功能：得到当前登录用户的userName
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @return
	 */
	public String getOwnUserName(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userName = shiroUser.getUserName();
		return userName;	
	}
	/**
	 * 功能：得到当前登录用户的信息
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @return
	 */
	public ShiroUser getShiroUser(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		return shiroUser;	
	}
	/**
	 * 功能：得到当前登录用户的的院系Id,Name
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @return academyUtil
	 */
	public AcademyUtil getUserAcademy(){
		Subject subject=SecurityUtils.getSubject();
		AcademyUtil academyUtil=(AcademyUtil) subject.getSession().getAttribute("CurrentUserAcademy");
		return academyUtil;
	}

}
