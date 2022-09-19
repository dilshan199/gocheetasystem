package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Booking;

public interface BookingManager {
	public List<Booking> getBookings() throws ClassNotFoundException, SQLException;
	public List<Booking> getBranches() throws ClassNotFoundException, SQLException;
	public List<Booking> getFilter(int branchId, String startDate, String endDate) throws ClassNotFoundException, SQLException;
}
