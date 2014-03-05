package com.tepusoft.service;

import com.tepusoft.entity.UsersRoles;

public interface UsersRolesServiceI {
public UsersRoles findUserRolesByUserId(String userId);
public void deleteUsersRoles(UsersRoles usersRoles);
}
