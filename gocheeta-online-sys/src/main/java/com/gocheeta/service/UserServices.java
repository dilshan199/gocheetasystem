package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.UserFactory;
import com.gocheeta.dao.UserManager;
import com.gocheeta.model.User;

@SuppressWarnings("unused")
public class UserServices {
	
	private static UserServices userServicesObj;

	private UserServices() {
		
	}
	
	public static synchronized UserServices servicesInstance() {
		if(userServicesObj == null) {
			userServicesObj = new UserServices();
		}
		
		return  userServicesObj;
	}
	
	//===================CRUD OPERATION==================//
	
	//Add Operation
	public boolean addUser(User user) throws ClassNotFoundException, SQLException {
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.addUser(user);
	}
	
	//Update operation
	public boolean editUser(User user) throws ClassNotFoundException, SQLException {
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.editUser(user);
	}
	
	//Fetch all users
	public List<User> getUsers() throws ClassNotFoundException, SQLException{
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.getUsers();
	}
	
	//Fetch selected user
	public User getUser(int userId) throws ClassNotFoundException, SQLException {
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.getUser(userId);
	}
	
	//Delete operation
	public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException {
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.deleteUser(userId);
	}
	
	//Fetch all branch details
	public List<User> getBranches() throws ClassNotFoundException, SQLException{
		UserManager userManager = UserFactory.getUserManagerIns();
		
		return userManager.getBranches();
	}
	
	//Check user exit
	public boolean checkUserExit(String userName) throws ClassNotFoundException, SQLException {
		 UserManager userManager = UserFactory.getUserManagerIns();
		 
		 return userManager.checkUserExit(userName);
	}
}
