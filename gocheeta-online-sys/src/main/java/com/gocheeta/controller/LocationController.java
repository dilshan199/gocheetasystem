package com.gocheeta.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gocheeta.model.Location;
import com.gocheeta.service.LocationServices;

public class LocationController extends HttpServlet {
	
	LocationServices services;
	int listSize;
	String message, branchIdError, streetError, cityError, distinationError, successMessage;
	boolean formError = false;
	private static final long serialVersionUID = 1L;
       
    public LocationController() {
       services = LocationServices.getServicesIns();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/locationedit":
			getSelectedLocation(request, response);
			break;
		case "/newlocation":
			getBranches(request, response);
			break;
		case "/locationdelete":
			deleteLocation(request, response);
			break;
			default: 
				getAllLocations(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null) {
			if(type.equals("submit")) {
				addLocation(request, response);
			}else {
				updateLocation(request, response);
			}
		}
	}
	
	private void getAllLocations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		List<Location> locationList;
		try {
			
			locationList = services.getLocations();
			listSize = locationList.size();
			
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			locationList = new ArrayList<Location>();
		}
		
		request.setAttribute("locationList", locationList);
		request.setAttribute("message", message);
		request.setAttribute("listSize", listSize);
		RequestDispatcher rd = request.getRequestDispatcher("location-list.jsp");
		rd.forward(request, response);
	}
	
	private void getBranches(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Location> branchList;
		try {
			
			branchList = services.branches();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			branchList = new ArrayList<Location>();
		}
		HttpSession hs = request.getSession();
		hs.setAttribute("branchList", branchList);
		response.sendRedirect("add-location.jsp");
	}
	
	private void addLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		double distination = Double.parseDouble(request.getParameter("distination"));
		
		if(branchId == 0) {
			branchIdError = "This feild is requierd";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(street.isEmpty()) {
			streetError = "This feild is requierd";
			formError = true;
			request.setAttribute("streetError", streetError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(city.isEmpty()) {
			cityError = "This feild is requierd";
			formError = true;
			request.setAttribute("cityError", cityError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(distination == 0) {
			distinationError = "This feild is requierd";
			formError = true;
			request.setAttribute("distinationError", distinationError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}else {
			Location location = new Location(branchId, street, city, distination);
			try {
				
				boolean result = services.addLocation(location);
				if(result) {
					successMessage = "Records saved successfully.";
				}else {
					message = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			
			request.setAttribute("location", location);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
	}
	
	private void deleteLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		Location location = new Location();
		try {
			
			boolean result = services.deleteLocation(locationId);
			if(result) {
				message = "Record deleted successfully";
			}else {
				message = "Record not delete successfull";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("location", location);
		request.setAttribute("message", message);
		response.sendRedirect("location");
	}
	
	private void getSelectedLocation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		clearMessage();
		
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		Location location = new Location();
		List<Location> branchList;
		try {
			
			location = services.getLocation(locationId);
			branchList = services.branches();
			
		} catch (ClassNotFoundException | SQLException e) {
			location = new Location();
			branchList = new ArrayList<Location>();
		}
		
		request.setAttribute("location", location);
		request.setAttribute("branchList", branchList);
		request.setAttribute("locationId", locationId);
		RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
		rd.forward(request, response);
	}
	
	private void updateLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		int locationId = Integer.parseInt(request.getParameter("locationId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		double distination = Double.parseDouble(request.getParameter("distination"));
		
		if(branchId == 0) {
			branchIdError = "This feild is requierd";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(street.isEmpty()) {
			streetError = "This feild is requierd";
			formError = true;
			request.setAttribute("streetError", streetError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(city.isEmpty()) {
			cityError = "This feild is requierd";
			formError = true;
			request.setAttribute("cityError", cityError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(distination == 0) {
			distinationError = "This feild is requierd";
			formError = true;
			request.setAttribute("distinationError", distinationError);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}else {
			Location location = new Location(locationId, branchId, street, city, distination);
			try {
				
				boolean result = services.editLocation(location);
				if(result) {
					successMessage = "Record successfully updated.";
				}else {
					message = "Record not updated successfully.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("location", location);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-location.jsp");
			rd.forward(request, response);
		}
	}
	
	public void clearMessage() {
		message = successMessage = branchIdError = streetError = cityError = distinationError = "";
	}

}
