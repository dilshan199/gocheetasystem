package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Driver;

public class DriverManagerImpl implements DriverManager{

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	@Override
	public boolean addDriver(Driver driver) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO driver (branchId,vehicleId,firstName,lastName,nic,contactNo,password) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, driver.getBranchId());
		ps.setInt(2, driver.getVehicleId());
		ps.setString(3, driver.getFirstName());
		ps.setString(4, driver.getLastName());
		ps.setString(5, driver.getNic());
		ps.setString(6, driver.getContactNo());
		ps.setString(7, driver.getPassword());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public boolean editDriver(Driver driver) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "UPDATE driver SET branchId = ?, vehicleId = ?, firstName = ?, lastName = ?, nic = ?, contactNo = ? WHERE driverId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, driver.getBranchId());
		ps.setInt(2, driver.getVehicleId());
		ps.setString(3, driver.getFirstName());
		ps.setString(4, driver.getLastName());
		ps.setString(5, driver.getNic());
		ps.setString(6, driver.getContactNo());
		ps.setInt(7, driver.getDriverId());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public List<Driver> getDrivers() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM driver INNER JOIN branch USING(branchId) INNER JOIN vehicle USING(vehicleId)";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Driver> driverList = new ArrayList<Driver>();
		while(rst.next()) {
			Driver driver = new Driver();
			driver.setDriverId(rst.getInt("driverId"));
			driver.setBranchId(rst.getInt("branchId"));
			driver.setVehicleId(rst.getInt("vehicleId"));
			driver.setFirstName(rst.getString("firstName"));
			driver.setLastName(rst.getString("lastName"));
			driver.setNic(rst.getString("nic"));
			driver.setContactNo(rst.getString("contactNo"));
			driver.setPassword(rst.getString("password"));
			driver.setBranchName(rst.getString("branchName"));
			driver.setVehicleNo(rst.getString("vehicleNo"));
			
			driverList.add(driver);
		}
		stmt.close();
		conn.close();
		
		return driverList;
	}

	@Override
	public Driver getDriver(int driverId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM driver WHERE driverId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, driverId);
		ResultSet rst = ps.executeQuery();
		Driver driver = new Driver();
		while(rst.next()) {
			driver.setDriverId(rst.getInt("driverId"));
			driver.setBranchId(rst.getInt("branchId"));
			driver.setVehicleId(rst.getInt("vehicleId"));
			driver.setFirstName(rst.getString("firstName"));
			driver.setLastName(rst.getString("lastName"));
			driver.setNic(rst.getString("nic"));
			driver.setContactNo(rst.getString("contactNo"));
		}
		ps.close();
		conn.close();
		
		return driver;
	}

	@Override
	public boolean deleteDriver(int driverId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "DELETE FROM driver WHERE driverId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, driverId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public boolean exitVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT vehicleId FROM driver WHERE vehhicleId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vehicleId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public boolean exitDriver(String nic) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT nic FROM driver WHERE nic = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nic);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public List<Driver> branches() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Driver> branchList = new ArrayList<Driver>();
		while(rst.next()) {
			Driver branch = new Driver();
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}

	@Override
	public List<Driver> vehicles() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM vehicle";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Driver> vehicleList = new ArrayList<Driver>();
		while(rst.next()) {
			Driver vehicle = new Driver();
			vehicle.setVehicleId(rst.getInt("vehicleId"));
			vehicle.setVehicleNo(rst.getString("vehicleNo"));
			
			vehicleList.add(vehicle);
		}
		stmt.close();
		conn.close();
		
		return vehicleList;
	}

	@Override
	public Driver getDriverProfile(int driverId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM driver INNER JOIN vehicle USING(vehicleId) WHERE driverId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, driverId);
		ResultSet rst = ps.executeQuery();
		Driver driver = new Driver();
		while(rst.next()) {
			driver.setFirstName(rst.getString("firstName"));
			driver.setLastName(rst.getString("lastName"));
			driver.setNic(rst.getString("nic"));
			driver.setContactNo(rst.getString("contactNo"));
			driver.setPassword(rst.getString("password"));
			driver.setVehicleNo(rst.getString("vehicleNo"));
		}
		ps.close();
		conn.close();
		
		return driver;
	}


}
