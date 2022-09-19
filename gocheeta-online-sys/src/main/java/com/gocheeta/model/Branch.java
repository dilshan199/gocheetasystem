package com.gocheeta.model;

public class Branch {
	
	private int branchId;
	private String branchName;
	private int  branchStatus;
	
	public Branch() {
		
	}

	public Branch(String branchName, int branchStatus) {
		this.branchName = branchName;
		this.branchStatus = branchStatus;
	}

	public Branch(int branchId, String branchName, int branchStatus) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchStatus = branchStatus;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
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
