package com.tepusoft.service;

import java.util.List;

import com.tepusoft.dto.MenuDto;
import com.tepusoft.entity.Menu;

public interface MenuServiceI {
	public List<Menu> findAllMenus();
	public void saveOrUpdateMenu(MenuDto menuDto);
	public void deleteMenu(String menuId, String menuPid);
	public List<Menu> findMenuByPid(String Pid);
	public List<Object[]> findAllMenuByRoleId(String roleId);
	public void saveRoleMenu(String roleId, String menuIds);
}
