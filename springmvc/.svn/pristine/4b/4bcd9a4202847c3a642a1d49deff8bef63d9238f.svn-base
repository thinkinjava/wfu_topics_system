package com.tepusoft.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.entity.StudentInfo;
import com.tepusoft.utils.PageUtil;

public interface StuInfServiceI {

	List<StudentInfo> findAllStuInfs(PageUtil pageUtil);
	List<StudentInfo> findUnTopicStuInfs(PageUtil pageUtil);
	List<StudentInfo> findUnTopicStuInfs(String majorId);
	BigInteger stuInfsCount();
	BigInteger stuUnTopCount();
	void saveStuInfo(StudentInfo studentInfo);
	void deleteStuInfo(StudentInfo studentInfo);
	void updateStuInfo(StudentInfoDto studentInfoDto);
	public StudentInfo findStuInfoById(String stutId);

	BigInteger stuInfosCountBySearch(String userName,String stuName);
	BigInteger stuInfosCountByUserName(String userName);
	BigInteger stuInfosCountByStuName(String stuName);
	List<StudentInfo> findStuInfosByUserNameAndStuName(String userName,String stuName);
	List<StudentInfo> findStuInfosByUserName(String userName);
	List<StudentInfo> findStuInfosByStuName(String stuName);

	StudentInfo findStuByUserName(String userName);

	void updateStuInfo2(StudentInfo studentInfo );
}
