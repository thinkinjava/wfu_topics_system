package com.tepusoft.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.dto.MenuModel;
import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.dto.UsersDto;
import com.tepusoft.entity.User;
import com.tepusoft.utils.PageUtil;

public interface UserServiceI {
	public String print();
	public List<User> findAllUsers();
	public void saveOrUpdateUser(UsersDto usersDto);
	public User findUserByUserName(String userName);
	public User findUserByUserId(String userId);
	public void deleteUser(User user);
	public List<MenuModel> findMenuListByUser();
	public BigInteger usersCount();
	public BigInteger usersCountBySearch(String userName);
	public List<User> findUsersByPageUtil(PageUtil pageUtil);
	public void deleteUserBySql(String userIds);
	public List<User> findUsersByUserName(String userName);
	public void saveUser(User user);
	public void updateUser(User user);
	public void saveUserAndUserRoles(StudentInfoDto studentInfoDto);
	public void executesql(String sql);

	
	
}
