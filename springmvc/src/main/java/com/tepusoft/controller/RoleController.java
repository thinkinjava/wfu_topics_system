package com.tepusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tepusoft.dto.RoleDto;
import com.tepusoft.entity.Role;
import com.tepusoft.service.RoleServiceI;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.JsonData;

@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController{
	@Autowired
	private RoleServiceI roleServiceI;
	
	/**
	 * 得到所有的角色信息
	 * @author Lijinzhao
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllRole")
	public List<Role> findAllRole(){		
		List<Role> roleList=roleServiceI.findAllRoles();
		for(Role r :roleList){
			r.setRolesMenus(null);
			r.setUsersRoles(null);
		}
		

		return roleList;
	}
	/**
	 * 功能：保存或修改角色信息
	 * @author Lijinzhao
	 * @time 2013-12-03
	 * @param roleDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdaterole")
	public JsonData saveOrUpdaterole(RoleDto roleDto){
		JsonData jsonData = new JsonData();
		roleServiceI.saveOrUpdaterole(roleDto);
		jsonData.setStatus("true");
		jsonData.setMessage("保存成功");
		return jsonData;
	}
	/**
	 * 功能删除角色
	 * @author Lijinzhao
	 * @time 2013-12-03
	 */
	@ResponseBody
	@RequestMapping("/deleteRole")
	public void deleteRole(String roleId){
		roleServiceI.deleteRole(roleId);
	}
}
