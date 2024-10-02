package com.mini3.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.mini3.dto.ActivateAccount;
import com.mini3.dto.Login;
import com.mini3.dto.User;
import com.mini3.entity.UserMaster;
import com.mini3.repository.UserMasterRepository;
import com.mini3.utils.AppConstrants;
import com.mini3.utils.EmailUtils;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private UserMasterRepository userMasterRepository;

	private EmailUtils emailUtils;

	public UserManagementServiceImpl(UserMasterRepository userMasterRepository, EmailUtils emailUtils) {
		this.userMasterRepository = userMasterRepository;
		this.emailUtils = emailUtils;
	}

	// Save User
	@Override
	public boolean saveUser(User user) {
		UserMaster entity = new UserMaster();
		BeanUtils.copyProperties(user, entity);
		entity.setPassword(generateRandomPassword());
		entity.setActiveStatus(AppConstrants.IN_ACTIVE);
		UserMaster save = userMasterRepository.save(entity);
		// send Registration Mail
		String subject = AppConstrants.YOUR_REGISTRATION_SUCCESS;
		String fileName = AppConstrants.REG_EMAIL_BODY_TXT;
		String body = readRegisterEmailBody(entity.getFullName(), entity.getPassword(), fileName);
		emailUtils.sendEmail(user.getEmail(), subject, body);
		return save.getUserId() != null;
	}

	// Activate User Account
	@Override
	public boolean activeUserAccount(ActivateAccount activateAccount) {
		UserMaster entity = new UserMaster();
		entity.setEmail(activateAccount.getEmail());
		entity.setPassword(activateAccount.getTempPassword());
		Example<UserMaster> example = Example.of(entity);
		List<UserMaster> allUsers = userMasterRepository.findAll(example);
		if (allUsers.isEmpty()) {
			return false;
		} else {
			UserMaster userMast = allUsers.get(0);
			userMast.setPassword(activateAccount.getNewPassword());
			userMast.setActiveStatus(AppConstrants.ACTIVE);
			userMasterRepository.save(userMast);
			return true;
		}
	}

	// Get All Users
	@Override
	public List<UserMaster> getAllUsers() {
		List<UserMaster> allusers = userMasterRepository.findAll();
		List<UserMaster> users = new ArrayList<>();
		for (UserMaster entity : allusers) {
			UserMaster user = new UserMaster();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		}
		return users;
	}

	// Get A User
	@Override
	public User getUserById(Integer userId) {
		Optional<UserMaster> userById = userMasterRepository.findById(userId);
		if (userById.isPresent()) {
			User user = new User();
			UserMaster userMaster = userById.get();
			BeanUtils.copyProperties(userMaster, user);
			return user;
		}
		return null;
	}

	// Delete A User By ID
	@Override
	public boolean deleteUserById(Integer userId) {
		boolean status = false;
		if (userMasterRepository.findById(userId).isPresent()) {
			try {
				userMasterRepository.deleteById(userId);
				status = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	// Change Status
	@Override
	public boolean changeActiveStatus(Integer userId, String activeStatus) {
		boolean status = false;
		Optional<UserMaster> userById = userMasterRepository.findById(userId);
		if (userById.isPresent()) {
			UserMaster userMast = userById.get();
			userMast.setActiveStatus(activeStatus);
			userMasterRepository.save(userMast);
			status = true;
		}
		return status;
	}

	// User login
	@Override
	public String login(Login login) {
		UserMaster entity = new UserMaster();
		entity.setEmail(login.getEmail());
		entity.setPassword(login.getPassword());
		Example<UserMaster> example = Example.of(entity);
		List<UserMaster> findAll = userMasterRepository.findAll(example);
		if (findAll == null) {
			return AppConstrants.INVALID_CREDENTIALS;
		} else {
			UserMaster userMaster = findAll.get(0);
			if (userMaster.getActiveStatus().equals(AppConstrants.ACTIVE)) {
				return AppConstrants.SUCCESS;
			} else {
				return AppConstrants.ACOUNT_NOT_ACTIVEATED;
			}
		}
	}

	// Forgot Password
	@Override
	public String forgotPassword(String email) {
		UserMaster entity = userMasterRepository.findByEmail(email);
		if(entity==null)
		{
			return AppConstrants.INVALID_EMAIL_ID;
		}
		String subject=AppConstrants.FORGOT_PASSWORD;
		String fileName=AppConstrants.RECOVER_EMAIL_BODY_TXT;
		String body=readRegisterEmailBody(email, entity.getPassword(), fileName);
		boolean sendMail=emailUtils.sendEmail(entity.getEmail(),subject,body);
		if(sendMail)
		{
			return AppConstrants.PASSWORD_SENT_TO_YOUR_REGISTERED_MAIL;
		}
		else
		{
			return null;
		}
	}

	// Generate Random Password
	static String generateRandomPassword() {
		String AlphaNumeric = AppConstrants.CAPITAL_ALPHAS + AppConstrants.NUMBERS + AppConstrants.SMALL_ALPHAS;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int index = (int) (AlphaNumeric.length() * Math.random());
			sb.append(AlphaNumeric.charAt(index));
		}
		return sb.toString();
	}

	String readRegisterEmailBody(String fullName, String pwd, String fileName) {
		String url = AppConstrants.URL;
		String mailBody = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			mailBody = sb.toString();
			mailBody = mailBody.replace(AppConstrants.FULL_NAME, fullName);
			mailBody = mailBody.replace(AppConstrants.TEMP_PWD, pwd);
			mailBody = mailBody.replace(AppConstrants.URLS, url);
			mailBody = mailBody.replace(AppConstrants.PWD, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public User updateUserById(Integer userId,User user) {
		UserMaster userMaster = userMasterRepository.findById(userId).orElse(null);
		if(userMaster!=null)
		{
			BeanUtils.copyProperties(user, userMaster);
			userMasterRepository.save(userMaster);
			User updatedUser=new User();
			BeanUtils.copyProperties(userMaster, updatedUser);
			return updatedUser;
		}
		else
		{
			throw new RuntimeException("User Not Found with this Id:"+userId);
		}
	}
}
