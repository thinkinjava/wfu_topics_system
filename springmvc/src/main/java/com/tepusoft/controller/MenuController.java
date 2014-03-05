package com.tepusoft.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tepusoft.dto.MenuDto;
import com.tepusoft.dto.RolesMenuDto;
import com.tepusoft.dto.TreeModel;
import com.tepusoft.entity.Menu;
import com.tepusoft.service.MenuServiceI;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.JsonData;

@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuServiceI menuServiceI;
	
	/**
	 * 得到所有的菜单信息，json形式返回
	 * @author Lijinzhao
	 * @time 2013-11-30
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllMenus")
	public List<TreeModel> findAllMenus(){
		
		List<Menu> menuList=menuServiceI.findAllMenus();
		List<TreeModel> treeModels=new ArrayList<>();
		for(Menu menu : menuList){
			TreeModel treeModel= new TreeModel();
			treeModel.setId(menu.getMenuId());
			treeModel.setName(menu.getMenuName());
			treeModel.setPid(menu.getMenuPid());
			treeModel.setType(menu.getType());
			treeModel.setUrl(menu.getUrl());
			treeModel.setDesc(menu.getDescription());

			treeModels.add(treeModel);
		}
		return treeModels;
	}
	/**
	 * 功能：添加或者更新菜单
	 * @author Lijinzhao
	 * @time 2013-11-30
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateMenu")
	public JsonData saveOrUpdateMenu(MenuDto menuDto){
		JsonData jsonData = new JsonData();
		try {
			menuServiceI.saveOrUpdateMenu(menuDto);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：删除菜单
	 * @author Lijinzhao
	 * @time 2013-11-30
	 * @param menuDto
	 */
	@ResponseBody
	@RequestMapping("/deleteMenu")
	public void deleteMenu(String menuId,String menuPid){
		menuServiceI.deleteMenu(menuId,menuPid);
	}
	/**
	 * 功能：根据roleId查找所有该角色的菜单
	 * @author Lijinzhao
	 * @time 2013-12-03
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllMenuByRoleId")
	public List<RolesMenuDto> findAllMenuByRoleId(String roleId){
		List<Object[]> list=menuServiceI.findAllMenuByRoleId(roleId);
		List<RolesMenuDto> rmList=new ArrayList<>();
	    for(Object[] l : list){  
	    	RolesMenuDto rolesMenuDto = new RolesMenuDto();
	    	rolesMenuDto.setRoleId(l[0].toString());
	    	rolesMenuDto.setMenuId(l[1].toString());
	    	rmList.add(rolesMenuDto);
    } 	 
		return rmList;
	}
	/**
	 * 功能：保存角色的权限信息（保存至中间表，sql保存）
	 * @param roleId
	 * @param menuIds
	 */
	@ResponseBody
	@RequestMapping("/saveRoleMenu")
	public void saveRoleMenu(String roleId,String menuIds){
		if(roleId.isEmpty()||menuIds.isEmpty()){
			//嘿嘿，不让他执行
		}else{
			menuServiceI.saveRoleMenu(roleId,menuIds);
		}
		
	}
}
