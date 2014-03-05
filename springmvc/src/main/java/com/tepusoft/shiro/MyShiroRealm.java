package com.tepusoft.shiro;

import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.tepusoft.entity.StudentInfo;
import com.tepusoft.entity.TeacherInfo;
import com.tepusoft.entity.User;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.Constants;



/**
* 类功能说明 TODO:自定义Realm
* @author Lijinzhao
*/

public class MyShiroRealm extends AuthorizingRealm
{
	 // 用于获取用户信息及用户权限信息的业务接口 
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@SuppressWarnings("unused")
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("rawtypes")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		//String username = (String) principals.fromRealm(getName()).iterator().next();
		 ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		 String username= shiroUser.getUserName();
		if (username != null)
		{
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 查询用户授权信息
			//info.addRole("admin");
			String sql=null;
			//超级管理员默认拥有所有操作权限
			if (Constants.SYSTEM_ADMINISTRATOR.equals(username))
			{
				sql="SELECT menus.menuId,menus.url FROM menus";
			}else {
				sql="SELECT menus.menuId,menus.url FROM\n" +
					"users\n" +
					"INNER JOIN users_roles ON users_roles.userId = users.userId\n" +
					"INNER JOIN roles ON users_roles.roleId = roles.roleId\n" +
					"INNER JOIN roles_menus ON roles_menus.roleId = roles.roleId\n" +
					"INNER JOIN menus ON roles_menus.menuId = menus.menuId WHERE users.userName='"+username+"'";	
			
			}
			 List perList = this.getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
			 if (perList!=null&&perList.size()!=0)
			{
				 for (Object object : perList)
					{
						 Object[] obj=(Object[])object;
						 if(obj[1]!=null&&!obj[1].toString().equals("")){
							 info.addStringPermission(obj[1].toString());
						 }
						 
					}
					return info;
			}
		}
		return null;
	}
	// 获取认证信息
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)throws AuthenticationException
	{
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken; 
		// 通过表单接收的用户名 
		 String username = token.getUsername(); 
		 String userType = token.getUserType();
		
		if (username != null && !"".equals(username))
		{
			SessionFactory s = this.getSessionFactory();
			String hql="from User u where u.userName=:name and u.userType=:userType";
			User users=(User)s.getCurrentSession().createQuery(hql).setParameter("name", username).setParameter("userType", userType).uniqueResult();
			if (users != null)
			{
				Subject subject=SecurityUtils.getSubject();
				if(!users.getUserName().equals("admin")){//如果不是超级管理员，则把用户信息放到session里。(超级管理员只有几个用户名为admin)
					if(userType.equals("1")){//如果当前登录的是教师
						String teaHql="from TeacherInfo t where t.userName=:userName";
						TeacherInfo teacherInfo =(TeacherInfo)s.getCurrentSession().createQuery(teaHql).setParameter("userName", username).uniqueResult();
						
						subject.getSession().setAttribute("CurrentUserAcademy",new AcademyUtil(teacherInfo.getTeaAcademy().getDicId(),teacherInfo.getTeaAcademy().getDicName()));
					}else if(userType.equals("2")){//如果当前登录的是学生
						String stuHql="from StudentInfo s where s.userName=:userName";
						StudentInfo studentInfo =(StudentInfo)s.getCurrentSession().createQuery(stuHql).setParameter("userName", username).uniqueResult();
						subject.getSession().setAttribute("CurrentUserAcademy",new AcademyUtil(studentInfo.getStuAcademy().getDicId(),studentInfo.getStuAcademy().getDicName()));
					}
				}

				subject.getSession().setAttribute(Constants.SHIRO_USER, new ShiroUser(users.getUserId(), users.getUserName(),users.getUserType(),users.getPersonName()));
				return new SimpleAuthenticationInfo(new ShiroUser(users.getUserId(), users.getUserName(),users.getUserType(),users.getPersonName()), users.getPassWord(),getName());
			}
		}
		return null;
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */

	public void clearCachedAuthorizationInfo(String principal )
	{
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
		principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */

	public void clearAllCachedAuthorizationInfo()
	{
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null)
		{
			for (Object key : cache.keys())
			{
				cache.remove(key);
			}
		}
	}


}
