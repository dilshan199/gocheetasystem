package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gocheeta.model.Login;

public class LoginManagerImpl implements LoginManager{
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}

	@Override
	public boolean exitUser(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT userName FROM user WHERE userName = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet rst = ps.executeQuery();
		boolean userExit = false;
		if(rst.next()) {
			userExit = true;
		}
		
		ps.close();
		conn.close();
		
		return userExit;
	}

	@Override
	public boolean checkPassword(String userName, String password) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT userName,password FROM user WHERE userName = ? AND password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rst = ps.executeQuery();
		boolean validPassword = false;
		if(rst.next()) {
			validPassword = true;
		}
		
		ps.close();
		conn.close();
		
		return validPassword;
	}

	@Override
	public Login getUser(String password) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM user WHERE password = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ResultSet rst = ps.executeQuery();
		Login login = new Login();
		while(rst.next()) {
			login.setUserId(rst.getInt("userId"));
			login.setBranchId(rst.getInt("branchId"));
			login.setUserType(rst.getString("userType"));
			login.setUserName(rst.getString("userName"));
			login.setPassword(rst.getString("password"));
		}
		ps.close();
		conn.close();
		
		return login;
	}

}
