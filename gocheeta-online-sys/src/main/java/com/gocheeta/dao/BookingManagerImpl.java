package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Booking;

public class BookingManagerImpl implements BookingManager{

	//Database connection
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	@Override
	public List<Booking> getBookings() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM booking INNER JOIN customer USING(cId) INNER JOIN branch USING(branchId) INNER JOIN vehicle USING(vehicleId)";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Booking> bookingList = new ArrayList<Booking>();
		while(rst.next()) {
			Booking booking = new Booking();
			booking.setBookingId(rst.getInt("bookingId"));
			booking.setPickUp(rst.getString("pickUp"));
			booking.setDrop(rst.getString("dropLocation"));
			booking.setPrice(rst.getDouble("price"));
			booking.setBookingStatus(rst.getInt("bookingStatus"));
			booking.setBranchName(rst.getString("branchName"));
			booking.setVehicleNo(rst.getString("vehicleNo"));
			booking.setFullName(rst.getString("fullName"));
			
			bookingList.add(booking);
		}
		
		stmt.close();
		conn.close();
		
		return bookingList;
	}

	@Override
	public List<Booking> getBranches() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM branch";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Booking> branchList = new ArrayList<Booking>();
		while(rst.next()) {
			Booking branch = new Booking();
			branch.setBranchId(rst.getInt("branchId"));
			branch.setBranchName(rst.getString("branchName"));
			
			branchList.add(branch);
		}
		stmt.close();
		conn.close();
		
		return branchList;
	}

	@Override
	public List<Booking> getFilter(int branchId, String startDate, String endDate) throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM booking INNER JOIN customer USING(cId) INNER JOIN branch USING(branchId) INNER JOIN vehicle USING(vehicleId) WHERE booking.branchId = ? AND DATE(bookingDate) BETWEEN ? AND ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, branchId);
		ps.setString(2, startDate);
		ps.setString(3, endDate);
		ResultSet rst = ps.executeQuery();
		List<Booking> bookingList = new ArrayList<Booking>();
		while(rst.next()) {
			Booking booking = new Booking();
			booking.setBookingId(rst.getInt("bookingId"));
			booking.setPickUp(rst.getString("pickUp"));
			booking.setDrop(rst.getString("dropLocation"));
			booking.setPrice(rst.getDouble("price"));
			booking.setBookingStatus(rst.getInt("bookingStatus"));
			booking.setBranchName(rst.getString("branchName"));
			booking.setVehicleNo(rst.getString("vehicleNo"));
			booking.setFullName(rst.getString("fullName"));
			booking.setBookingDate(rst.getString("bookingDate"));
			
			bookingList.add(booking);
		}
		ps.close();
		conn.close();
		
		return bookingList;
	}

}
