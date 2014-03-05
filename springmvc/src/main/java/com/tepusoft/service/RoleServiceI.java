package com.tepusoft.service;

import java.util.List;

import com.tepusoft.dto.RoleDto;
import com.tepusoft.entity.Role;

public interface RoleServiceI {
	public List<Role> findAllRoles();
	public Role findRoleById(String roleId);
	public void saveOrUpdaterole(RoleDto roleDto);
	public void deleteRole(String roleId);
	public Role findRoleByRoleName(String roleName);
}
