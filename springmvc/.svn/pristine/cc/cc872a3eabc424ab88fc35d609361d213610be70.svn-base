package com.tepusoft.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.TeacherInfoDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Dept;
import com.tepusoft.entity.Role;
import com.tepusoft.entity.TeacherInfo;
import com.tepusoft.entity.Troom;
import com.tepusoft.entity.User;
import com.tepusoft.entity.UsersRoles;
import com.tepusoft.service.TeaServiceI;
import com.tepusoft.service.UserServiceI;
import com.tepusoft.utils.PageUtil;

@Service
public class TeaServiceImpl implements TeaServiceI {
	@Autowired
	private BaseDaoI<TeacherInfo> baseDaoI;
	@Autowired
	private BaseDaoI<UsersRoles> urBaseDaoI;
	@Autowired
	private BaseDaoI<User> userBaseDaoI;
	@Autowired
	private UserServiceI userServiceI;
	
	@Override
	public List<TeacherInfo> findAllTeas(PageUtil pageUtil, String academyId) {
		// TODO Auto-generated method stub
		String hql = "from TeacherInfo t where t.teaAcademy.dicId=:academyId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public BigInteger teasCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from wfu_teacherinfo";
		return baseDaoI.countBySql(sql);
	}
	@Override
	public void saveTeacher(TeacherInfoDto teacherInfoDto) {
		// TODO Auto-generated method stub
		//首先保存user表
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setPassWord(teacherInfoDto.getPassWord());
		user.setPersonName(teacherInfoDto.getTeaName());
		user.setUserName(teacherInfoDto.getUserName());
		user.setUserType("1");
		userBaseDaoI.save(user);
		
		//保存信息表
		TeacherInfo teacherInfo = new TeacherInfo();
		BeanUtils.copyProperties(teacherInfoDto, teacherInfo, new String[]{"teaId","teaTitle","teaTroom","teaAcademy","teaDept"});
		teacherInfo.setTeaId(UUID.randomUUID().toString());
		//1保存职称
		DataDictionary title = new DataDictionary();
		title.setDicId(teacherInfoDto.getTeaTitle());
		teacherInfo.setTeaTitle(title);
		//2保存院系
		DataDictionary academy = new DataDictionary();
		academy.setDicId(teacherInfoDto.getTeaAcademyId());
		teacherInfo.setTeaAcademy(academy);
		//3保存教研室
		Troom troom = new Troom();
		troom.setTroomId(teacherInfoDto.getTeaTroom());
		teacherInfo.setTeaTroom(troom);
		teacherInfo.setCreateTime(new Date());
		//4保存部门
		if(!StringUtils.isEmpty(teacherInfoDto.getTeaDept())){
			Dept dept = new Dept();
			dept.setDeptId(teacherInfoDto.getTeaDept());
			teacherInfo.setTeaDept(dept);			
		}
		if(StringUtils.isEmpty(teacherInfo.getTeaAge())){
			teacherInfo.setTeaAge(0);
		}
		baseDaoI.save(teacherInfo);

		//保存用户权限
		if(!StringUtils.isEmpty(teacherInfoDto.getRoleIds())){
			
			String roleIds[]=teacherInfoDto.getRoleIds().split(",");
			for(String s:roleIds){
				UsersRoles usersRoles = new UsersRoles();
				usersRoles.setId(UUID.randomUUID().toString());
				usersRoles.setUsers(user);
				Role role = new Role();
				role.setRoleId(s);
				usersRoles.setRoles(role);
				urBaseDaoI.save(usersRoles);
			}
		}
		
		
	}

	@Override
	public void updateTeacher(TeacherInfoDto teacherInfoDto) {
		// TODO Auto-generated method stub
		TeacherInfo teacherInfo = new TeacherInfo();
		BeanUtils.copyProperties(teacherInfoDto, teacherInfo,new String[]{"teaTitle","teaTroom","createTime","teaDept","teaAcademy"});
		if(!StringUtils.isEmpty(teacherInfoDto.getTeaTitle())){
			DataDictionary title= new DataDictionary();//职称存放在数据字典里
			title.setDicId(teacherInfoDto.getTeaTitle());
			teacherInfo.setTeaTitle(title);
		}
		if(!StringUtils.isEmpty(teacherInfoDto.getTeaTroom())){
			Troom troom = new Troom();
			troom.setTroomId(teacherInfoDto.getTeaTroom());
			teacherInfo.setTeaTroom(troom);
		}
		if(!StringUtils.isEmpty(teacherInfoDto.getTeaDept())){
			Dept dept = new Dept();
			dept.setDeptId(teacherInfoDto.getTeaDept());
			teacherInfo.setTeaDept(dept);
		}
		if(!StringUtils.isEmpty(teacherInfoDto.getTeaAcademyId())){
			DataDictionary academy= new DataDictionary();//院系存放在数据字典里
			academy.setDicId(teacherInfoDto.getTeaAcademyId());
			teacherInfo.setTeaAcademy(academy);
		}
		baseDaoI.update(teacherInfo);
		
		//保存用户权限
		if(!StringUtils.isEmpty(teacherInfoDto.getRoleIds())){
			User user = userServiceI.findUserByUserName(teacherInfoDto.getUserName());
			this.delUsersRolesBySql(user.getUserId());
			String roleIds[]=teacherInfoDto.getRoleIds().split(",");
			for(String s:roleIds){
				UsersRoles usersRoles = new UsersRoles();
				usersRoles.setId(UUID.randomUUID().toString());
				usersRoles.setUsers(user);
				Role role = new Role();
				role.setRoleId(s);
				usersRoles.setRoles(role);
				urBaseDaoI.save(usersRoles);
			}
		}
	}


	public void delUsersRolesBySql(String userId){
		String sql="DELETE FROM users_roles where userId='"+userId+"'";
		urBaseDaoI.executeSql(sql);
		}

	@Override
	public void deleteTeacher(String teaId,String userName) {
		// TODO Auto-generated method stub
		User user = userServiceI.findUserByUserName(userName);
		if(user!=null){
			String sql="DELETE FROM users_roles where userId='"+user.getUserId()+"'";  //删除角色中间表
			urBaseDaoI.executeSql(sql);
			userBaseDaoI.delete(user); //删除用户表
		}
		
		String sql2="DELETE FROM wfu_teacherinfo where teaId='"+teaId+"'";
		baseDaoI.executeSql(sql2);
		
	}
	@Override
	public TeacherInfo findByUserName(String userName) {
		// TODO Auto-generated method stub
		String hql="from TeacherInfo t where t.userName=:userName";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("userName", userName);
		return baseDaoI.get(hql,map);
	}

	@Override
	public TeacherInfo findTeaByUserName(String userName) {
		String hql = "from TeacherInfo t where t.userName='"+userName+"'";
		return baseDaoI.get(hql);
	}
	@Override
	public void updateTeaInfo(TeacherInfo teacherInfo) {
		baseDaoI.update(teacherInfo);
	}
	@Override
	public BigInteger teaInfosCountByUserName(String userName) {
		String sql = "select count(*) from wfu_teacherinfo w where w.userName like '%"+userName+"%'";
		return baseDaoI.countBySql(sql);
	}
	@Override
	public BigInteger teaInfosCountByTeaName(String teaName) {
		String sql = "select count(*) from wfu_teacherinfo w where w.teaName like '%"+teaName+"%'";
		return baseDaoI.countBySql(sql);
	}
	@Override
	public BigInteger teaInfosCountByUserNameAndTeaName(String userName,
			String teaName) {
		String sql = "select count(*) from wfu_teacherinfo w where w.userName like '%"+teaName+"%' and w.teaName like '%"+teaName+"%'";
		return baseDaoI.countBySql(sql);
	}
	@Override
	public List<TeacherInfo> findTeaInfosByUserName(String userName) {
		String hql = "from TeacherInfo t where t.userName like '%"+userName+"%'";
		return baseDaoI.find(hql);
	}
	@Override
	public List<TeacherInfo> findTeaInfosByTeaName(String teaName) {
		String hql = "from TeacherInfo t where t.teaName like '%"+teaName+"%'";
		return baseDaoI.find(hql);
	}

	@Override
	public List<TeacherInfo> findTeaInfosByUserNameAndTeaName(String userName,
			String teaName) {
		String hql = "from TeacherInfo t where t.userName like '%"+teaName+"%' and t.teaName like '"+teaName+"'";
		return baseDaoI.find(hql);
	}

}
