package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Branch;

public interface BranchManager {
	public boolean addBranch(Branch branch) throws ClassNotFoundException, SQLException;
	public List<Branch> getBranchs() throws ClassNotFoundException, SQLException;
	public Branch getBranch(int branchId) throws ClassNotFoundException, SQLException;
	public boolean editBranch(Branch branch) throws ClassNotFoundException, SQLException;
	public boolean deleteBranch(int branchId) throws ClassNotFoundException, SQLException;
}
