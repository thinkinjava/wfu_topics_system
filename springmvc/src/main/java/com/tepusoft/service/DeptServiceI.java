package com.tepusoft.service;

import java.util.List;

import com.tepusoft.dto.DeptDto;
import com.tepusoft.dto.TreeModel;
import com.tepusoft.entity.Dept;

public interface DeptServiceI {
	public List<Dept> findAllDepts();
	public void deleDept(Dept dept);
	public Dept findDeptByDeptId(String deptId);
	public List<Dept> findDeptsByPidNull();
	public List<Dept> findDeptsByPid(String id);
	public List<TreeModel> findDeptsOutJson();
	public void saveOrUpdateMenu(DeptDto deptDto);
	public void deleDeptOrChildDept(String deptId);  //用sql语句删除
	public Dept findDeptByDeptName(String dept);
}
