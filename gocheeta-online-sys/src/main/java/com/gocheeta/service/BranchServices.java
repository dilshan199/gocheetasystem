package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.BranchFactory;
import com.gocheeta.dao.BranchManager;
import com.gocheeta.model.Branch;

@SuppressWarnings("unused")
public class BranchServices {

	private static BranchServices branchServicesObj;
	
	private BranchServices() {
		
	}
	
	public static synchronized BranchServices servicesInstance() {
		if(branchServicesObj == null) {
			branchServicesObj = new BranchServices();
		}
		
		return branchServicesObj;
	}
	
	//=================CRUD OPERATION===========================//

	//Add Operation
	public boolean addBranch(Branch branch) throws ClassNotFoundException, SQLException {
		BranchManager branchManager = BranchFactory.getBranchManagerIns();
		
		return branchManager.addBranch(branch);
	}
	
	//Get all row
	public Branch getBranch(int branchId) throws ClassNotFoundException, SQLException{
		BranchManager branchManager = BranchFactory.getBranchManagerIns();
		
		return branchManager.getBranch(branchId);
	}
	
	//Get selected row
	public List<Branch> getBranchs() throws ClassNotFoundException, SQLException {
		BranchManager branchManager = BranchFactory.getBranchManagerIns();
		
		return branchManager.getBranchs();
	}
	
	//Update Operation
	public boolean editBranch(Branch branch) throws ClassNotFoundException, SQLException {
		BranchManager branchManager = BranchFactory.getBranchManagerIns();
		
		return branchManager.editBranch(branch);
	}
	
	//Delete Operation
	public boolean deleteBranch(int branchId) throws ClassNotFoundException, SQLException {
		BranchManager branchManager = BranchFactory.getBranchManagerIns();
		
		return branchManager.deleteBranch(branchId);
	}

}
