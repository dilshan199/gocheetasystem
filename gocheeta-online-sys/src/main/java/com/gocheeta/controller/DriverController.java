package com.gocheeta.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gocheeta.model.Driver;
import com.gocheeta.service.DriverServices;

@SuppressWarnings("unused")
public class DriverController extends HttpServlet {
	
	DriverServices services;
	String message, branchIdError, vehicleIdError, firstNameError, lastNameError, nicError, contactNoError, successMessage;
	int listSize;
	boolean formError = false;
	private static final long serialVersionUID = 1L;
    
    public DriverController() {
        services = DriverServices.servicesIns();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/newdriver":
			getStoreRecords(request, response);
			break;
		case "/driveredit":
			getSelectedDriver(request, response);
			break;
		case "/driverdelete":
			deleteDriver(request, response);
			break;
		case "/profile":
			getDriverProfile(request, response);
			break;
			default:
				getAllDrivers(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null) {
			if(type.equals("submit")) {
				addDrivers(request, response);
			}else {
				updateDriver(request, response);
			}
		}
	}
	
	private void getStoreRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Driver> branchList;
		List<Driver> vehicleList;
		try {
			
			branchList = services.branches();
			vehicleList = services.vehicles();
			
		} catch (ClassNotFoundException | SQLException e) {
			branchList = new ArrayList<Driver>();
			vehicleList = new ArrayList<Driver>();
		}
		
		HttpSession hs = request.getSession();
		hs.setAttribute("branchList", branchList);
		hs.setAttribute("vehicleList", vehicleList);
		response.sendRedirect("add-driver.jsp");
	}
	
	private void getAllDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		List<Driver> driverList;
		try {
			driverList = services.getDrivers();
			listSize = driverList.size();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			driverList = new ArrayList<Driver>();
		}
		
		request.setAttribute("message", message);
		request.setAttribute("listSize", listSize);
		request.setAttribute("driverList", driverList);
		RequestDispatcher rd = request.getRequestDispatcher("driver-list.jsp");
		rd.forward(request, response);
	}
	
	private void addDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		clearMessage();
		
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String nic = request.getParameter("nic");
		String contactNo = request.getParameter("contactNo");
		String password = null;
		
		if(branchId == 0) {
			branchIdError = "This field is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleId == 0) {
			vehicleIdError = "This field is required";
			formError = true;
			request.setAttribute("vehicleId", vehicleIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(firstName.isEmpty()) {
			firstNameError = "This field is required";
			formError = true;
			request.setAttribute("firstNameError", firstNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(lastName.isEmpty()) {
			lastNameError = "This field is required";
			formError = true;
			request.setAttribute("lastNameError", lastNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(nic.isEmpty()) {
			nicError = "This field is required";
			formError = true;
			request.setAttribute("nicError", nicError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(contactNo.isEmpty()) {
			contactNoError = "This field is required";
			formError = true;
			request.setAttribute("contactNoError", contactNoError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}else {
			if(contactNo.length() != 10) {
				contactNoError = "Contact no must include 10 digits";
				formError = true;
				request.setAttribute("contactNoError", contactNoError);
				RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
				rd.forward(request, response);
			}
		}
		
		password = generatePassword();
		
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}else {
			Driver driver = new Driver(branchId, vehicleId, firstName, lastName, nic, contactNo, password);
			try {
				boolean result = services.addDriver(driver);
				if(result) {
					successMessage = "Records saved successfully.";
				}else {
					message = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("driver", driver);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
	}
	
	private void getSelectedDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		int driverId = Integer.parseInt(request.getParameter("driverId"));
		
		Driver driver = new Driver();
		List<Driver> branchList;
		List<Driver> vehicleList;
		try {
			driver = services.getDriver(driverId);
			branchList = services.branches();
			vehicleList = services.vehicles();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			driver = new Driver();
			branchList = new ArrayList<Driver>();
			vehicleList = new ArrayList<Driver>();
		}
		request.setAttribute("driver", driver);
		request.setAttribute("branchList", branchList);
		request.setAttribute("vehicleList", vehicleList);
		request.setAttribute("driverId", driverId);
		RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
		rd.forward(request, response);
	}
	
	private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		
		int driverId = Integer.parseInt(request.getParameter("driverId"));
		
		Driver driver = new Driver();
		try {
			boolean result = services.deleteDriver(driverId);
			if(result) {
				message = "Record deleted successfully";
			}else {
				message = "Record not delete successfull";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("driver", driver);
		request.setAttribute("message", message);
		response.sendRedirect("driver");
	}
	
	private void updateDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int driverId = Integer.parseInt(request.getParameter("driverId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String nic = request.getParameter("nic");
		String contactNo = request.getParameter("contactNo");
		
		if(branchId == 0) {
			branchIdError = "This field is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleId == 0) {
			vehicleIdError = "This field is required";
			formError = true;
			request.setAttribute("vehicleId", vehicleIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(firstName.isEmpty()) {
			firstNameError = "This field is required";
			formError = true;
			request.setAttribute("firstNameError", firstNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(lastName.isEmpty()) {
			lastNameError = "This field is required";
			formError = true;
			request.setAttribute("lastNameError", lastNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(nic.isEmpty()) {
			nicError = "This field is required";
			formError = true;
			request.setAttribute("nicError", nicError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
		
		if(contactNo.isEmpty()) {
			contactNoError = "This field is required";
			formError = true;
			request.setAttribute("contactNoError", contactNoError);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}else {
			if(contactNo.length() != 10) {
				contactNoError = "Contact no must include 10 digits";
				formError = true;
				request.setAttribute("contactNoError", contactNoError);
				RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
				rd.forward(request, response);
			}
		}
		
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}else {
			Driver driver = new Driver(driverId, branchId, vehicleId, firstName, lastName, nic, contactNo);
			try {
				boolean result = services.editDriver(driver);
				if(result) {
					successMessage = "Record successfully updated.";
				}else {
					message = "Record not updated successfully.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("driver", driver);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("add-driver.jsp");
			rd.forward(request, response);
		}
	}
	
	private void getDriverProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int driverId = Integer.parseInt(request.getParameter("driverId"));
		Driver driver = new Driver();
		try {
			driver = services.getDriverProfile(driverId);
		} catch (ClassNotFoundException | SQLException e) {
			driver = new Driver();
		}
		request.setAttribute("driver", driver);
		RequestDispatcher rd = request.getRequestDispatcher("driver-profile.jsp");
		rd.forward(request, response);
		
	}
	
	public void clearMessage() {
		message = branchIdError = vehicleIdError = firstNameError = lastNameError = nicError = contactNoError = successMessage = "";
	}
	
	//Generate and Encrypt password
	public String generatePassword(){
		//Generate random password for new driver registration
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String visiblePassword = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    String password = visiblePassword;
	    
		return password;
	}

}
