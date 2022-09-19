package com.gocheeta.model;

public class User {
	private int userId;
	private int branchId;
	private String userType;
	private String userName;
	protected String password;
	private int userStatus;
	private String branchName;
	private int branchStatus;
	
	public User() {
		
	}

	public User(int branchId, String userType, String userName, String password, int userStatus) {
		this.branchId = branchId;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.userStatus = userStatus;
	}

	public User(int userId, int branchId, String userType, String userName, String password, int userStatus) {
		this.userId = userId;
		this.branchId = branchId;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.userStatus = userStatus;
	}
	
	public User(int branchId, String branchName, int branchStatus) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchStatus = branchStatus;
	}
	
	public User(int userId, int branchId, String userType, String userName) {
		super();
		this.userId = userId;
		this.branchId = branchId;
		this.userType = userType;
		this.userName = userName;
	}

	public User(int userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public User(String userName) {
		this.userName = userName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getBranchStatus() {
		return branchStatus;
	}
	
	public void setBranchStatus(int branchStatus) {
		this.branchStatus = branchStatus;
	}
}
