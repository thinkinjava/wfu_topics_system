package com.tepusoft.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.dto.RoleDto;
import com.tepusoft.entity.Role;
import com.tepusoft.service.RoleServiceI;
@Service
public class RoleServiceImpl implements RoleServiceI {

	@Autowired
	public BaseDaoI<Role> baseDaoI;
	@Autowired
	public BaseDaoI baseDaoI2;
	
	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		String hql="from Role r";
		return baseDaoI.find(hql);
	}
	@Override
	public Role findRoleById(String roleId) {
		// TODO Auto-generated method stub
		return baseDaoI.get(Role.class, roleId);
	}
	@Override
	public void saveOrUpdaterole(RoleDto roleDto) {
		// TODO Auto-generated method stub
		Role role = new Role();	
		BeanUtils.copyProperties(roleDto, role, new String[]{"roleId,createTime,modifyTime"});
		if(roleDto.getRoleId()==null||roleDto.getRoleId().equals("")){
			role.setRoleId(UUID.randomUUID().toString());
			role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}else{
			role.setRoleId(roleDto.getRoleId());
		}
		baseDaoI.saveOrUpdate(role);
	}
	@Override
	public void deleteRole(String roleId) {  //删除角色之前要把角色的权限信息删除
		// TODO Auto-generated method stub
		String hql="delete from RolesMenus rm where rm.id='"+roleId+"'";
		baseDaoI2.executeHql(hql);
		Role role = baseDaoI.get(Role.class, roleId);
		baseDaoI.delete(role);
	}
	@Override
	public Role findRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		String hql="from Role r where r.roleName=:roleName";
		Map<String,Object> map= new HashMap<String, Object>();
		map.put("roleName", roleName);
		return baseDaoI.get(hql, map);
	}

}
