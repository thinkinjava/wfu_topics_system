package com.tepusoft.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.MenuModel;
import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.dto.UsersDto;
import com.tepusoft.entity.User;
import com.tepusoft.entity.UsersRoles;
import com.tepusoft.service.RoleServiceI;
import com.tepusoft.service.UserServiceI;
import com.tepusoft.shiro.ShiroUser;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.PageUtil;
@Service
public class UserServiceImpl implements UserServiceI{
	@Autowired
	private BaseDaoI<User> baseDaoI;
	@Autowired
	private RoleServiceI roleServiceI;
	@Autowired
	private BaseDaoI<UsersRoles> baseDaoI2;


	public String print(){
		//System.out.println("print spring集成成功");
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from User u");
		return baseDaoI.find(hql.toString());
	}

	@Override
	public void saveOrUpdateUser(UsersDto usersDto) {
		// TODO Auto-generated method stub
		User user = new User();
		BeanUtils.copyProperties(usersDto, user,new String[]{"userId","dept"});
		if(StringUtils.isEmpty(usersDto.getUserId())){   //id为空说明是添加用户
			user.setUserId(UUID.randomUUID().toString());
			//user.setCreateTime(new Date());
		}else{
			user.setUserId(usersDto.getUserId());
		}

		// user.setDept(usersDto.getDept());
		 baseDaoI.saveOrUpdate(user);
		 //保存完成用户后，保存它的权限
		/* if(usersDto.getRoleIds()!=null&&!usersDto.getRoleIds().equals("")){
			 String roles[]=usersDto.getRoleIds().split(",");
			 for(int i=0;i<roles.length;i++){
				 Role role = new Role();
				 role.setRoleId(roles[i]);
				 UsersRoles usersRoles = new UsersRoles();
				 usersRoles.setId(UUID.randomUUID().toString());
				 usersRoles.setRoles(role);
				 usersRoles.setUsers(user);
				 roleBaseDaoI.save(usersRoles);
			 }
		 }*/

	}
	/**
	 * 根据用户名查找用户信息
	 */
	public User findUserByUserName(String userName){
		String hql="from User u where u.userName = '"+userName+"'";
		List<User> list=baseDaoI.find(hql);
		if(list.size()==0){
			return null;
		}
		return (User) list.get(0);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

		baseDaoI.delete(user);
	}
	
	public List<MenuModel> findMenuListByUser(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser) subject.getSession().getAttribute("shiroUser");
		String sql=null;
		//超级管理员默认拥有所有功能权限
		if (Constants.SYSTEM_ADMINISTRATOR.equals(shiroUser.getUserName()))
		{
			sql="SELECT menus.menuId, menus.menuName, menus.menuPid, menus.url, menus.iconCls FROM menus";
		}
		else 
		{
			sql="SELECT DISTINCT "+
					"menus.menuId, menus.menuName, menus.menuPid, menus.url, menus.iconCls "+
					"FROM "+
					"menus "+
					"INNER JOIN roles_menus ON roles_menus.menuId = menus.menuId "+
					"INNER JOIN roles ON roles_menus.roleId = roles.roleId "+
					"INNER JOIN users_roles ON users_roles.roleId = roles.roleId "+
					"INNER JOIN users ON users_roles.userId = '"+shiroUser.getUserId()+"' order by menus.num asc";
		}

		List<?> listmenu = baseDaoI.findBySqlWithOutType(sql);
		List<MenuModel> menuModelList=new ArrayList<>();
		for (Object object : listmenu){
			Object[] objs=(Object[])object;
			String id=String.valueOf(objs[0]);
			String pid=String.valueOf(objs[2]);
			
			if(pid.endsWith("null")||pid.equals("")){
				MenuModel menuModel = new MenuModel();
				menuModel.setName(String.valueOf(objs[1]));
				menuModel.setIconCls(String.valueOf(objs[4]));
				menuModel.setUrl(String.valueOf(objs[3]));
				List<MenuModel> child = new ArrayList<>();
				for (Object object2 : listmenu) {
					Object[] objs2=(Object[])object2;
					String pid2=String.valueOf(objs2[2]);
					if(pid2.equals(id)){
						MenuModel menuModel2 = new MenuModel();
						menuModel2.setName(String.valueOf(objs2[1]));
						menuModel2.setIconCls(String.valueOf(objs2[4]));
						menuModel2.setUrl(String.valueOf(objs2[3]));
						child.add(menuModel2);
					}
				}
				menuModel.setChild(child);
				menuModelList.add(menuModel);
			}
		}
		return menuModelList;
		
		}

	@Override
	public User findUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return baseDaoI.get(User.class, userId);
	}

	@Override
	public BigInteger usersCount() {
		// TODO Auto-generated method stub
		String hql="select count(*) from users";
		return baseDaoI.countBySql(hql);
	}

	@Override
	public List<User> findUsersByPageUtil(PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql="from User u";	
		return baseDaoI.find(hql, pageUtil.getPage(), pageUtil.getRows());
		
		
	}

	@Override
	public void deleteUserBySql(String userIds) {
//		for (int i = 0; i < 1000000; i++) {
//			String sql="insert into users(userId,userName,passWord,age) values('"+UUID.randomUUID().toString()+"'," +
//					"'bbbfff','ddss23',35)";
//			baseDaoI.executeSql(sql);
//		}
	
		String ids[]=userIds.split(",");

		String str="(";
		for(int i=0;i<ids.length;i++){
			if(i>0){
				str+=",";
				}
			str+="'"+ids[i]+"'";
			}
		str+=")";
		StringBuffer hql=new StringBuffer("delete UsersRoles u where u.users.userId in");
		StringBuffer hql2= new StringBuffer("delete User u where u.userId in");
		hql.append(str);
		hql2.append(str);
		baseDaoI.executeHql(hql.toString());
		baseDaoI.executeHql(hql2.toString());
		}
	/*根据用户名模糊查询*/
	@Override
	public List<User> findUsersByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql="from User u where u.userName like '%"+userName+"%'";
		return baseDaoI.find(hql);
		}

	@Override
	public BigInteger usersCountBySearch(String userName) {
		// TODO Auto-generated method stub
		String hql="select count(*) from users u where u.userName like '%"+userName+"%'";
		return baseDaoI.countBySql(hql);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

		baseDaoI.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		baseDaoI.update(user);
	}

	@Override
	public void saveUserAndUserRoles(StudentInfoDto studentInfoDto) {
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName(studentInfoDto.getUserName());
		user.setPassWord(studentInfoDto.getPassWord());
		user.setPersonName(studentInfoDto.getStuName());
		user.setUserType("2");//1是教师 2是学生
		baseDaoI.save(user);
		//下面开始保存到中间表
		UsersRoles usersRoles = new UsersRoles();
		usersRoles.setId(UUID.randomUUID().toString());
		usersRoles.setRoles(roleServiceI.findRoleById(studentInfoDto.getRoleIds()));
		usersRoles.setUsers(user);
		baseDaoI2.save(usersRoles);
	}

	@Override
	public void executesql(String sql) {
		// TODO Auto-generated method stub
		baseDaoI.executeSql(sql);
	}
	
	}

