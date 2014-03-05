package com.tepusoft.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.dto.TeacherInfoDto;
import com.tepusoft.entity.TeacherInfo;
import com.tepusoft.utils.PageUtil;

public interface TeaServiceI {
	public List<TeacherInfo> findAllTeas(PageUtil pageUtil, String academyId);
	public BigInteger teasCount();

	public void saveTeacher(TeacherInfoDto teacherInfoDto);
	public void updateTeacher(TeacherInfoDto teacherInfoDto);
	public void deleteTeacher(String teaId,String userName);
	public TeacherInfo findByUserName(String userName);//通过userName查找教师信息

	public TeacherInfo findTeaByUserName(String userName);
	public void updateTeaInfo(TeacherInfo teacherInfo);
	public BigInteger teaInfosCountByUserName(String userName);
	public BigInteger teaInfosCountByTeaName(String teaName);
	public BigInteger teaInfosCountByUserNameAndTeaName(String userName,String teaName);
	public List<TeacherInfo> findTeaInfosByUserName(String userName);
	public List<TeacherInfo> findTeaInfosByTeaName(String teaName);
	public List<TeacherInfo> findTeaInfosByUserNameAndTeaName(String userName,String teaName);
	


}
