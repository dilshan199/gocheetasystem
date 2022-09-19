package com.gocheeta.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gocheeta.model.Booking;
import com.gocheeta.service.BookingServices;

public class BookingController extends HttpServlet {
	BookingServices services;
	String message;
	int listSize;
	private static final long serialVersionUID = 1L;
    
    public BookingController() {
       services = BookingServices.bookingServicesIns();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/filter":
			filterBooking(request, response);
			break;
			default:
				getAllBooking(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type.equals("filter")) {
			filterBooking(request, response);
		}
	}
	
	private void getAllBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		List<Booking> bookingList;
		List<Booking> branchList;
		try {
			
			bookingList = services.getBookings();
			branchList = services.getBranches();
			listSize = bookingList.size();
			
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			bookingList = new ArrayList<Booking>();
			branchList = new ArrayList<Booking>();
		}
		request.setAttribute("bookingList", bookingList);
		request.setAttribute("branchList", branchList);
		request.setAttribute("message", message);
		request.setAttribute("listSize", listSize);
		RequestDispatcher rd = request.getRequestDispatcher("booking-list.jsp");
		rd.forward(request, response);
	}
	
	private void filterBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		double totalSales = 0;
		List<Booking> bookingList;
		try {
			
			bookingList = services.getFilter(branchId, startDate, endDate);
			listSize = bookingList.size();
			
			//Get total sales
			for(Booking ts : bookingList) {
				totalSales += ts.getPrice();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			bookingList = new ArrayList<Booking>();
		}
		request.setAttribute("bookingList", bookingList);
		request.setAttribute("listSize", listSize);
		request.setAttribute("totalSales", totalSales);
		RequestDispatcher rd = request.getRequestDispatcher("filter.jsp");
		rd.forward(request, response);
	}
	
	public void clearMessage() {
		message = "";
	}

}
