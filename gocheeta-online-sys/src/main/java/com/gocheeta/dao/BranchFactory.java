package com.gocheeta.dao;

public class BranchFactory {
	public static BranchManager getBranchManagerIns() {
		return new BranchManagerImpl();
	}

}
