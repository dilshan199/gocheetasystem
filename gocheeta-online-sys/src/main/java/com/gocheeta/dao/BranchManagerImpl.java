package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Branch;

public class BranchManagerImpl implements BranchManager{
	
	//Database connection
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}

	public boolean addBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO branch (branchName,branchStatus) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, branch.getBranchName());
		ps.setInt(2, branch.getBranchStatus());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public List<Branch> getBranchs() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Branch> branchList = new ArrayList<Branch>();
		while(rst.next()) {
			Branch branch = new Branch();
			
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			branch.setBranchStatus(rst.getInt("branchStatus"));
			
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}

	public Branch getBranch(int branchId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch WHERE branchId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		ResultSet rst = ps.executeQuery();
		Branch branch = new Branch();
		while(rst.next()) {
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			branch.setBranchStatus(rst.getInt("branchStatus"));
		}
		ps.close();
		conn.close();
		
		return branch;
	}

	public boolean editBranch(Branch branch) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "UPDATE branch SET branchName = ?,branchStatus = ? WHERE branchId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, branch.getBranchName());
		ps.setInt(2, branch.getBranchStatus());
		ps.setInt(3, branch.getBranchId());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public boolean deleteBranch(int branchId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "DELETE FROM branch WHERE branchId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}


}
