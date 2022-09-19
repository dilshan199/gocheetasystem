package com.gocheeta.model;

public class Login {

	private int userId;
	private int branchId;
	private String userName;
	private String userType;
	protected String password;
	
	public Login() {
		
	}
	
	public Login(String userName) {
		
		this.userName = userName;
	}

	public Login(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
