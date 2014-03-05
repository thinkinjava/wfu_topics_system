package com.tepusoft.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.DeptDto;
import com.tepusoft.dto.TreeModel;
import com.tepusoft.entity.Dept;
import com.tepusoft.service.DeptServiceI;
import com.tepusoft.utils.Constants;
@Service
public class DeptServiceImpl implements DeptServiceI{

	@Autowired
	private BaseDaoI<Dept> baseDaoI;
	
	@Override
	public List<Dept> findAllDepts() {
		// TODO Auto-generated method stub
		String hql="from Dept d";
		return baseDaoI.find(hql);
	}
	
	public void deleDept(Dept dept){
		baseDaoI.delete(dept);
	}

	@Override
	public Dept findDeptByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return baseDaoI.get(Dept.class, deptId);
	}

	@Override
	public List<Dept> findDeptsByPidNull() {
		// TODO Auto-generated method stub
		String hql="from Dept d where d.parentId is null";
		return baseDaoI.find(hql);
	}

	@Override
	public List<Dept> findDeptsByPid(String id) {
		// TODO Auto-generated method stub
		String hql="from Dept d where d.parentId=:id";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		return baseDaoI.find(hql, params);
	}

	@Override
	public List<TreeModel> findDeptsOutJson() {
		// TODO Auto-generated method stub
		String hql="from Dept d";
		List<Dept> tempList = baseDaoI.find(hql);
		List<TreeModel> list=new ArrayList<TreeModel>();
		for (Dept o : tempList)
		{
			TreeModel treeModel=new TreeModel();
			treeModel.setId(o.getDeptId()+Constants.NULL_STRING);
			treeModel.setPid(o.getParentId()==null?null:o.getParentId().toString());
			treeModel.setName(o.getDeptName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			//treeModel.setIconCls(o.getIconCls());
			list.add(treeModel);
		}
		return list;
	}

	@Override
	public void saveOrUpdateMenu(DeptDto deptDto) {
		// TODO Auto-generated method stub
		Dept dept = new Dept();
		if(deptDto.getDeptId()==null||deptDto.getDeptId().equals("")){
			dept.setDeptId(UUID.randomUUID().toString());
		}else{
			dept.setDeptId(deptDto.getDeptId());
		}
		
		BeanUtils.copyProperties(deptDto, dept, new String[]{"deptId","creatTime"});
		dept.setCreatTime(new Date());
		baseDaoI.saveOrUpdate(dept);
	}

	@Override
	public void deleDeptOrChildDept(String deptId) {
		// TODO Auto-generated method stub
		String hql="delete Dept t where t.deptId=:deptId or t.parentId=:deptId";
		Map<String,Object> map=new HashMap<>();
		map.put("deptId", deptId);
		baseDaoI.executeHql(hql, map);
	}

	@Override
	public Dept findDeptByDeptName(String dept) {
		// TODO Auto-generated method stub
		String hql="from Dept d where d.deptName=:dept";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dept", dept);
		return baseDaoI.get(hql, map);
	}

}
