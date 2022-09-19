package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.BookingFactory;
import com.gocheeta.dao.BookingManager;
import com.gocheeta.model.Booking;

@SuppressWarnings("unused")
public class BookingServices {
	
	private static BookingServices bookingServicesObj;

	private BookingServices() {
		
	}
	
	public static synchronized BookingServices bookingServicesIns() {
		if(bookingServicesObj == null) {
			bookingServicesObj = new BookingServices();
		}
		
		return bookingServicesObj;
	}
	
	//=================== CRUD OPERATION ==========================//
	
	//Get all rows
	public List<Booking> getBookings() throws ClassNotFoundException, SQLException{
		BookingManager bm = BookingFactory.getBookingManagerIns();
		
		return bm.getBookings();
	}
	
	//Get branches
	public List<Booking> getBranches() throws ClassNotFoundException, SQLException{
		BookingManager bm = BookingFactory.getBookingManagerIns();
		
		return bm.getBranches();
	}
	
	//Get filter Result
	public List<Booking> getFilter(int branchId, String startDate, String endDate) throws ClassNotFoundException, SQLException{
		BookingManager bm =BookingFactory.getBookingManagerIns();
		
		return bm.getFilter(branchId, startDate, endDate);
	}
}
