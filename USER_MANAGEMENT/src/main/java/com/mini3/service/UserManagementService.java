package com.mini3.service;

import java.util.List;

import com.mini3.dto.ActivateAccount;
import com.mini3.dto.Login;
import com.mini3.dto.User;
import com.mini3.entity.UserMaster;

public interface UserManagementService 
{
	boolean saveUser(User user);
	
	boolean activeUserAccount(ActivateAccount activateAccount);
	
	List<UserMaster> getAllUsers();
	
	User getUserById(Integer userId);
	
	boolean deleteUserById(Integer userId);
	
	boolean changeActiveStatus(Integer userId,String activeStatus);
	
	String login(Login login);
	
	String forgotPassword(String email);
	
	User updateUserById(Integer userId,User user);
	
}