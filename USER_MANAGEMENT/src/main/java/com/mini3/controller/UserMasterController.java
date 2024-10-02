package com.mini3.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mini3.dto.ActivateAccount;
import com.mini3.dto.Login;
import com.mini3.dto.User;
import com.mini3.entity.UserMaster;
import com.mini3.service.UserManagementService;
import com.mini3.utils.AppConstrants;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping(value = "/usermanagement")
public class UserMasterController 
{
	private UserManagementService userManagementService;

	public UserMasterController(UserManagementService userManagementService)
	{
		this.userManagementService = userManagementService;
	}
	
	//POST
	@PostMapping(value = "/userreg")
	ResponseEntity<String> userRegsitration(@RequestBody User user)
	{
		boolean saveUser = userManagementService.saveUser(user);
		if (saveUser) return new ResponseEntity<>(AppConstrants.REGISTRATION_SUCCESSFULLY,HttpStatus.CREATED);
		else return new ResponseEntity<>(AppConstrants.REGISTRATION_FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//POST
	@PostMapping(value = "/activate")
	ResponseEntity<String> activateAcount(@RequestBody ActivateAccount acc)
	{
		boolean activeUserAccount = userManagementService.activeUserAccount(acc);
		if(activeUserAccount) return new ResponseEntity<>(AppConstrants.ACCOUNT_ACTIVATED,HttpStatus.OK);
		else return new ResponseEntity<>(AppConstrants.INVALID_TEMPOARY_PASSWORD,HttpStatus.BAD_REQUEST);
	}
	
	//GET
	@GetMapping(value = "/users")
	ResponseEntity<List<UserMaster>> getAllUsers()
	{
		List<UserMaster> allUsers = userManagementService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
	
	//GET
	@GetMapping(value = "/user/{userId}")
	ResponseEntity<User> getAUser(@PathVariable Integer userId)
	{
		User user = userManagementService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping(value = "/user/{userId}")
	ResponseEntity<String> deleteAUser(@PathVariable Integer userId)
	{
		boolean deleteUser = userManagementService.deleteUserById(userId);
		if(deleteUser) return new ResponseEntity<>(AppConstrants.DELETED,HttpStatus.OK);
		else return new ResponseEntity<>(AppConstrants.FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//GET
	@PutMapping(value = "/status/{userId}/{activeStatus}")
	ResponseEntity<String> statusChange(@PathVariable Integer userId,@PathVariable String activeStatus)
	{
		boolean isChange = userManagementService.changeActiveStatus(userId, activeStatus);
		if(isChange) return new ResponseEntity<>(AppConstrants.STATUS_CHANGED,HttpStatus.OK);
		else return new ResponseEntity<>(AppConstrants.FAILED_TO_CHANGE,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//POST
	@PostMapping(value = "/login")
	ResponseEntity<String> login(@RequestBody Login login)
	{
		String logine = userManagementService.login(login);
		return new ResponseEntity<>(logine,HttpStatus.OK);
	}
	
	//POST
	@PostMapping(value = "/forgotpassword/{email}")
	ResponseEntity<String> forgotPassword(@PathVariable String email)
	{
		String forgotPassword = userManagementService.forgotPassword(email);
		return new ResponseEntity<>(forgotPassword,HttpStatus.OK);
	}
	
	//PUT
	@PutMapping(value = "/update/{userId}")
	ResponseEntity<User> updateUser(@PathVariable Integer userId,@RequestBody User user)
	{
		User updateUser = userManagementService.updateUserById(userId, user);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
}