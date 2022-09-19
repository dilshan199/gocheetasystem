package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Location;

public class LocationManagerImpl implements LocationManager{

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	@Override
	public boolean addLocation(Location location) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO distination (branchId,street,city,distination) VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, location.getBranchId());
		ps.setString(2, location.getStreet());
		ps.setString(3, location.getCity());
		ps.setDouble(4, location.getDistination());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public boolean editLocation(Location location) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "UPDATE distination SET branchId = ?, street = ?, city = ?, distination = ? WHERE locationId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, location.getBranchId());
		ps.setString(2, location.getStreet());
		ps.setString(3, location.getCity());
		ps.setDouble(4, location.getDistination());
		ps.setInt(5, location.getLocationId());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public Location getLocation(int locationId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM distination WHERE locationId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, locationId);
		ResultSet rst = ps.executeQuery();
		Location location = new Location();
		while(rst.next()) {
			location.setLocationId(rst.getInt("locationId"));
			location.setBranchId(rst.getInt("branchId"));
			location.setStreet(rst.getString("street"));
			location.setCity(rst.getString("city"));
			location.setDistination(rst.getDouble("distination"));
		}
		ps.close();
		conn.close();
		
		return location;
	}

	@Override
	public List<Location> getLocations() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM distination INNER JOIN branch USING(branchId)";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Location> locationList = new ArrayList<Location>();
		while(rst.next()) {
			Location location = new Location();
			location.setLocationId(rst.getInt("locationId"));
			location.setBranchId(rst.getInt("branchId"));
			location.setStreet(rst.getString("street"));
			location.setCity(rst.getString("city"));
			location.setDistination(rst.getDouble("distination"));
			location.setBranchName(rst.getString("branchName"));
			
			locationList.add(location);
		}
		stmt.close();
		conn.close();
		
		return locationList;
	}

	@Override
	public boolean deleteLocation(int locationId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "Delete FROM distination WHERE locationId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, locationId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public List<Location> branches() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Location> branchList = new ArrayList<Location>();
		while(rst.next()) {
			Location branch = new Location();
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}


}
