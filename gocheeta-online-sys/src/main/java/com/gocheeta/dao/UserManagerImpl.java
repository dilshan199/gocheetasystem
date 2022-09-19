package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.User;

public class UserManagerImpl implements UserManager{
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	public boolean addUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO user (branchId,userName,userType,password,userStatus) VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getBranchId());
		ps.setString(2, user.getUserName());
		ps.setString(3, user.getUserType());
		ps.setString(4, user.getPassword());
		ps.setInt(5, user.getUserStatus());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public boolean editUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "UPDATE user SET branchId = ?,userName = ?,userType = ? WHERE userId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getBranchId());
		ps.setString(2, user.getUserName());
		ps.setString(3, user.getUserType());
		ps.setInt(4, user.getUserId());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public List<User> getUsers() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM user INNER JOIN branch USING(branchId)";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<User> userList = new ArrayList<User>();
		while(rst.next()) {
			User user = new User();
			user.setUserId(rst.getInt("userId"));
			user.setBranchId(rst.getInt("branchId"));
			user.setUserName(rst.getString("userName"));
			user.setUserType(rst.getString("userType"));
			user.setBranchName(rst.getString("branchName"));
			
			userList.add(user);
		}
		stmt.close();
		conn.close();
		
		return userList;
	}

	public User getUser(int userId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM user INNER JOIN branch USING(branchId) WHERE userId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rst = ps.executeQuery();
		User user = new User();
		while(rst.next()) {
			user.setUserId(rst.getInt("userId"));
			user.setBranchId(rst.getInt("branchId"));
			user.setUserName(rst.getString("userName"));
			user.setUserType(rst.getString("userType"));
			user.setPassword(rst.getString("password"));
			user.setUserStatus(rst.getInt("userStatus"));
		}
		ps.close();
		conn.close();
		
		return user;
	}

	public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "DELETE FROM user WHERE userId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public List<User> getBranches() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<User> branchList = new ArrayList<User>();
		while(rst.next()) {
			User branch = new User();
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			branch.setBranchStatus(rst.getInt("branchStatus"));
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}
	
	public boolean checkUserExit(String userName) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		String sql = "SELECT * FROM user WHERE userName = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userName);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

}
