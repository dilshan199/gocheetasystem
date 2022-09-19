package com.gocheeta.service;

import java.sql.SQLException;

import com.gocheeta.dao.LoginFactory;
import com.gocheeta.dao.LoginManager;
import com.gocheeta.model.Login;

public class LoginServices {

	private static LoginServices loginServicesObj;
	
	private LoginServices() {
		
	}
	
	public static synchronized LoginServices servicesIns() {
		if(loginServicesObj == null) {
			loginServicesObj = new LoginServices();
		}
		
		return loginServicesObj;
	}
	
	//Check user exit
	public boolean exitUser(String userName) throws ClassNotFoundException, SQLException {
		LoginManager lm = LoginFactory.getLoginIns();
		
		return lm.exitUser(userName);
	}
	
	//Check password is valid
	public boolean checkPassword(String userName, String password) throws ClassNotFoundException, SQLException {
		LoginManager lm = LoginFactory.getLoginIns();
		
		return lm.checkPassword(userName, password);
	}
	
	public Login getUser(String password) throws ClassNotFoundException, SQLException {
		LoginManager lm = LoginFactory.getLoginIns();
		
		return lm.getUser(password);
	}

}
