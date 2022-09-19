package com.gocheeta.dao;

public class BookingFactory {

	public static BookingManager getBookingManagerIns() {
		return new BookingManagerImpl();
	}

}
