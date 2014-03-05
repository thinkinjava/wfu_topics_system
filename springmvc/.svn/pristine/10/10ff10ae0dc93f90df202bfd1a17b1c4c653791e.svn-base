package com.tepusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tepusoft.dto.DeptDto;
import com.tepusoft.entity.Dept;
import com.tepusoft.service.DeptServiceI;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.JsonData;

@Controller
@RequestMapping("/deptController")
public class DeptController extends BaseController{
	
	@Autowired
	private DeptServiceI deptServiceI;
	
	/**
	 * 功能：获取所有部门信息
	 * @author Lijinzhao
	 * @time 2103-12-04
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllDept")
	public List<Dept> findAllDept(){
		List<Dept> deptList=deptServiceI.findAllDepts();
		return deptList;
	}
	/**
	 * 功能：保存或者修改部门
	 * @author Lijinzhao
	 * @time 2013-12-04
	 * @param deptDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateDept")
	public JsonData saveOrUpdateDept(DeptDto deptDto){
		JsonData jsonData = new JsonData();
		try {
			jsonData.setStatus("true");
			jsonData.setMessage("保存成功");
			deptServiceI.saveOrUpdateMenu(deptDto);
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
			jsonData.setMessage("保存失败");
		}

		return jsonData;
	}
	
	/**
	 * 功能：删除选中部门及子部门
	 * @author Lijinzhao
	 * @time 2013-12-04
	 * @param deptId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletedept")
	public JsonData deletedept(String deptId){
		JsonData jsonData = new JsonData();
		try {
			jsonData.setStatus("true");
			jsonData.setMessage("删除部门成功");
			deptServiceI.deleDeptOrChildDept(deptId);
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
			jsonData.setMessage("删除部门失败");
		}
		return jsonData;
	}
}
