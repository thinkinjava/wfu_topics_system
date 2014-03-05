package com.tepusoft.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Major;
import com.tepusoft.entity.Role;
import com.tepusoft.entity.StudentInfo;
import com.tepusoft.entity.User;
import com.tepusoft.entity.UsersRoles;
import com.tepusoft.service.DataDictionaryServiceI;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.service.StuInfServiceI;
import com.tepusoft.service.UserServiceI;
import com.tepusoft.utils.PageUtil;
@Service
public class StuInfServiceImpl implements StuInfServiceI{
	@Autowired
	private BaseDaoI<StudentInfo> baseDaoI;
	@Autowired
	private BaseDaoI<UsersRoles> baseDaoI2;
	@Autowired
	private DataDictionaryServiceI dataDictionaryServiceI;
	@Autowired
	private MajorServiceI majorServiceI;
	@Autowired
	private UserServiceI userServiceI;
	@Override
	public List<StudentInfo> findAllStuInfs(PageUtil pageUtil) {
		StringBuffer hql = new StringBuffer("from StudentInfo s");
		return baseDaoI.find(hql.toString(),pageUtil.getPage(),pageUtil.getRows());
	}

	@Override
	public BigInteger stuInfsCount() {
		String sql = "select count(*) from wfu_studentinfo";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public void saveStuInfo(StudentInfo studentInfo) {
		baseDaoI.save(studentInfo);
	}

	@Override
	public StudentInfo findStuInfoById(String stutId) {
		return baseDaoI.get(StudentInfo.class, stutId);
	}

	@Override
	public void deleteStuInfo(StudentInfo studentInfo) {
		baseDaoI.delete(studentInfo);
		
	}

	@Override
	public BigInteger stuInfosCountBySearch(String userName,String stuName) {
		String sql = "select count(*) from wfu_studentinfo w where w.userName like '%"+userName+"%' and w.stuName like '%"+stuName+"%'";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public List<StudentInfo> findStuInfosByUserNameAndStuName(String userName,String stuName) {
		String hql = "from StudentInfo s where s.userName like '%"+userName+"%' and s.stuName like '%"+stuName+"%'";
		return baseDaoI.find(hql);
	}

	@Override
	public BigInteger stuInfosCountByUserName(String userName) {
		String sql = "select count(*) from  wfu_studentinfo w where w.userName like '%"+userName+"%'";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public BigInteger stuInfosCountByStuName(String stuName) {
		String sql = "select count(*) from  wfu_studentinfo w where w.stuName like '%"+stuName+"%'";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public List<StudentInfo> findStuInfosByUserName(String userName) {
		String hql = "from StudentInfo s where s.userName like '%"+userName+"%'";
		return baseDaoI.find(hql);
	}

	@Override
	public List<StudentInfo> findStuInfosByStuName(String stuName) {
		String hql = "from StudentInfo s where s.stuName like '%"+stuName+"%'";
		return baseDaoI.find(hql);
	}

	@Override
	public void updateStuInfo(StudentInfoDto studentInfoDto) {
		StudentInfo studentInfo = new StudentInfo();
		BeanUtils.copyProperties(studentInfoDto, studentInfo,new String[]{"stuMajor","stuAcademy"});
		//下面开始更新院系
		if(!StringUtils.isEmpty(studentInfoDto.getStuAcademyId())){
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary = dataDictionaryServiceI.findDataDicById(studentInfoDto.getStuAcademyId());
			studentInfo.setStuAcademy(dataDictionary);
		}
		//下面开始更新专业
		if(!StringUtils.isEmpty(studentInfoDto.getStuMajor())){
			Major major = new Major();
			major = majorServiceI.findMajorByMajorId(studentInfoDto.getStuMajor());
			studentInfo.setStuMajor(major);
		}
		baseDaoI.update(studentInfo);
		//下面开始更新权限表
		if(!StringUtils.isEmpty(studentInfoDto.getRoleIds())){
			User user = userServiceI.findUserByUserName(studentInfoDto.getUserName());
			this.delUsersRolesBySql(user.getUserId());
			String roleIds[]=studentInfoDto.getRoleIds().split(",");
			for(String s:roleIds){
				UsersRoles usersRoles = new UsersRoles();
				usersRoles.setId(UUID.randomUUID().toString());
				usersRoles.setUsers(user);
				Role role = new Role();
				role.setRoleId(s);
				usersRoles.setRoles(role);
				baseDaoI2.save(usersRoles);
			}
		}
	}
	public void delUsersRolesBySql(String userId){
		String sql="DELETE FROM users_roles where userId='"+userId+"'";
		baseDaoI2.executeSql(sql);
		}

	@Override
	public StudentInfo findStuByUserName(String userName) {
		String hql = "from StudentInfo s where s.userName='"+userName+"'";
		return baseDaoI.get(hql);
	}

	@Override
	public void updateStuInfo2(StudentInfo studentInfo) {
		baseDaoI.update(studentInfo);
		
	}

	@Override
	public List<StudentInfo> findUnTopicStuInfs(PageUtil pageUtil) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from StudentInfo s where s.stutId not in ( select c.studentId.stutId from ChooseTopic c )");
		return baseDaoI.find(hql.toString(),pageUtil.getPage(),pageUtil.getRows());
	}
	
	@Override
	public BigInteger stuUnTopCount() {
		String sql = "select count(*) from wfu_studentinfo where stutId not in(select studentId from wfu_choosetopic)";
		return baseDaoI.countBySql(sql);
	}
	@Override
	public List<StudentInfo> findUnTopicStuInfs(String majorId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from StudentInfo s where s.stutId not in ( select c.studentId.stutId from ChooseTopic c ) "
				+ "and s.stuMajor.majorId ='"+majorId+"'");
		return baseDaoI.find(hql.toString());
	}
}
