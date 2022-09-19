package com.gocheeta.dao;

import java.sql.SQLException;

import com.gocheeta.model.Login;

public interface LoginManager {
	public boolean exitUser(String userName) throws ClassNotFoundException, SQLException;
	public boolean checkPassword(String userName, String password) throws ClassNotFoundException, SQLException;
	public Login getUser(String password) throws ClassNotFoundException, SQLException;
}
