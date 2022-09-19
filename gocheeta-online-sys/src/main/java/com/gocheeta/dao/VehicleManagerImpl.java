package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Vehicle;

public class VehicleManagerImpl implements VehicleManager{

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	@Override
	public boolean addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "INSERT INTO vehicle (catId,branchId,image,vehicleNo,vehicleName,maxPassenger,pricePerKm) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vehicle.getCatId());
		ps.setInt(2, vehicle.getBranchId());
		ps.setString(3, vehicle.getImage());
		ps.setString(4, vehicle.getVehicleNo());
		ps.setString(5, vehicle.getVehicleName());
		ps.setInt(6, vehicle.getMaxPassenger());
		ps.setDouble(7, vehicle.getPricePerKm());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public boolean editVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "UPDATE vehicle SET catId = ?,branchId = ?,vehicleNo = ?,vehicleName = ?,maxPassenger = ?,pricePerKm = ? WHERE vehicleId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vehicle.getCatId());
		ps.setInt(2, vehicle.getBranchId());
		ps.setString(3, vehicle.getVehicleNo());
		ps.setString(4, vehicle.getVehicleName());
		ps.setInt(5, vehicle.getMaxPassenger());
		ps.setDouble(6, vehicle.getPricePerKm());
		ps.setInt(7, vehicle.getVehicleId());
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public Vehicle getVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM vehicle INNER JOIN category USING(catId) INNER JOIN branch USING(branchId) WHERE vehicleId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vehicleId);
		ResultSet rst = ps.executeQuery();
		Vehicle vehicle = new Vehicle();
		while(rst.next()) {
			vehicle.setVehicleId(rst.getInt("vehicleId"));
			vehicle.setCatId(rst.getInt("catId"));
			vehicle.setBranchId(rst.getInt("branchId"));
			vehicle.setImage(rst.getString("image"));
			vehicle.setVehicleNo(rst.getString("vehicleNo"));
			vehicle.setVehicleName(rst.getString("vehicleName"));
			vehicle.setMaxPassenger(rst.getInt("maxPassenger"));
			vehicle.setPricePerKm(rst.getDouble("pricePerKm"));
		}
		ps.close();
		conn.close();
		
		return vehicle;
	}

	@Override
	public List<Vehicle> getVehicles() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM vehicle INNER JOIN category USING(catId) INNER JOIN branch USING(branchId)";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		while(rst.next()) {
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleId(rst.getInt("vehicleId"));
			vehicle.setCatId(rst.getInt("catId"));
			vehicle.setBranchId(rst.getInt("branchId"));
			vehicle.setImage(rst.getString("image"));
			vehicle.setVehicleNo(rst.getString("vehicleNo"));
			vehicle.setVehicleName(rst.getString("vehicleName"));
			vehicle.setMaxPassenger(rst.getInt("maxPassenger"));
			vehicle.setPricePerKm(rst.getDouble("pricePerKm"));
			vehicle.setCategory(rst.getString("category"));
			vehicle.setBranchName(rst.getString("branchName"));
			vehicle.setVehicleStatus(rst.getInt("vehicleStatus"));
			
			vehicleList.add(vehicle);
		}
		stmt.close();
		conn.close();
		
		return vehicleList;
	}

	@Override
	public boolean deleteVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "DELETE FROM vehicle WHERE vehicleId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, vehicleId);
		int result = ps.executeUpdate();
		ps.close();
		conn.close();
		
		return result > 0;
	}

	@Override
	public List<Vehicle> categories() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM category";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Vehicle> categoryList = new ArrayList<Vehicle>();
		while(rst.next()) {
			Vehicle category = new Vehicle();
			category.setCatId(rst.getInt("catId"));
			category.setCategory(rst.getString("category"));
			
			categoryList.add(category);
		}
		stmt.close();
		conn.close();
		
		return categoryList;
	}

	@Override
	public List<Vehicle> branches() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Vehicle> branchList = new ArrayList<Vehicle>();
		while(rst.next()) {
			Vehicle branch = new Vehicle();
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}

	@Override
	public boolean exitVehicle(String vehicleNo) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM vehicle WHERE vehicleId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vehicleNo);
		int result = ps.executeUpdate();
				
		return result > 0;
	}

}
