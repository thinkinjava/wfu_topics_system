package com.tepusoft.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.MenuDto;
import com.tepusoft.entity.Menu;
import com.tepusoft.service.MenuServiceI;
@Service
public class MenuServiceImpl implements MenuServiceI {
	
	@Autowired
	private BaseDaoI<Menu> baseDaoI;

	@Override
	public List<Menu> findAllMenus() {
		// TODO Auto-generated method stub
		String hql="from Menu u";
		return baseDaoI.find(hql);
	}
	@Override
	public void saveOrUpdateMenu(MenuDto menuDto) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		if(menuDto.getMenuId()==null||menuDto.getMenuId().equals("")){
			menu.setMenuId(UUID.randomUUID().toString());
		}else{
			menu.setMenuId(menuDto.getMenuId());
		}
		BeanUtils.copyProperties(menuDto, menu, new String[]{"menuId"});
		baseDaoI.saveOrUpdate(menu);
	}
	@Override
	public void deleteMenu(String menuId, String menuPid) {
		// TODO Auto-generated method stub
		if(menuPid==null||menuPid.equals("")||menuPid.equals("undefined")){   //pid为空时说明是父节点
			List<Menu> childMenu=this.findMenuByPid(menuId);
			for(Menu m:childMenu){             //删除所有子节点
				baseDaoI.delete(m); 
			}
			Menu menu = baseDaoI.get(Menu.class,menuId);   //删除父节点
			baseDaoI.delete(menu);
		}else{
			Menu menu = baseDaoI.get(Menu.class,menuId);
			baseDaoI.delete(menu);
		}
		
		
		
	}
	@Override
	public List<Menu> findMenuByPid(String Pid) {
		// TODO Auto-generated method stub
		String hql="from Menu u where u.menuPid='"+Pid+"'";
		return baseDaoI.find(hql);
	}
	@Override
	public List<Object[]> findAllMenuByRoleId(String roleId) {
		// TODO Auto-generated method stub
		String sql="select roleId,menuId from roles_menus where roleId='"+roleId+"'";
		return baseDaoI.findBySql(sql);
	}
	@Override
	public void saveRoleMenu(String roleId, String menuIds) {
		// TODO Auto-generated method stub
		String deleteSql="delete from roles_menus where roles_menus.roleId='"+roleId+"'";
		baseDaoI.executeSql(deleteSql);
		String Ids[]=menuIds.split(",");
		for(String str:Ids){
			String sql="insert into roles_menus(id,roleId,menuId) values('"+UUID.randomUUID().toString()+"'," +
					"'"+roleId+"','"+str+"')";
			baseDaoI.executeSql(sql);
		}
		
	}

}
