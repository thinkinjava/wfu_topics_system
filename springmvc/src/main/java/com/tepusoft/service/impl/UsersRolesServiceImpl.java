package com.tepusoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.UsersRoles;
import com.tepusoft.service.UsersRolesServiceI;

@Service
public class UsersRolesServiceImpl implements UsersRolesServiceI {
	@Autowired
	private BaseDaoI<UsersRoles> baseDaoI;

	@Override
	public UsersRoles findUserRolesByUserId(String userId) {
		String hql = "from UsersRoles u where u.users.userId='"+userId+"'";
		return baseDaoI.get(hql);
	}

	@Override
	public void deleteUsersRoles(UsersRoles usersRoles) {
		baseDaoI.delete(usersRoles);
	}
	
}
