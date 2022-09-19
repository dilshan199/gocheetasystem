package com.gocheeta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.gocheeta.model.Vehicle;
import com.gocheeta.service.VehicleServices;

@MultipartConfig
public class VehicleController extends HttpServlet {
	
	VehicleServices services;
	String message, catIdError, branchIdError, imageError, vehicleNoError, vehicleNameError, maxPassengerError, pricePerKmError, successMessage, errorMessage;
	int listSize;
	boolean formError;
	private static final long serialVersionUID = 1L;
    
    public VehicleController() {
        services = VehicleServices.servicesInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/vehicleedit":
			getSelectedVehicle(request, response);
			break;
		case "/vehicledelete":
			deleteVehicle(request, response);
			break;
		case "/newvehicle":
			getCategories(request, response);
			break;
			default:
				getAllVehicles(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type != null) {
			if(type.equals("submit")) {
				addVehicle(request, response);
			}else {
				updateVehicle(request, response);
			}
		}
	}
	
	private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		Part part = request.getPart("image");
		String image = part.getSubmittedFileName();
		String vehicleNo = request.getParameter("vehicleNo");
		String vehicleName = request.getParameter("vehicleName");
		int maxPassenger = Integer.parseInt(request.getParameter("maxPassenger"));
		double pricePerKm = Double.parseDouble(request.getParameter("pricePerKm"));
		
		if(catId == 0) {
			catIdError = "This feild is required";
			formError = true;
			request.setAttribute("catIdError", catIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(branchId == 0) {
			branchIdError = "This feild is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(image.isEmpty()) {
			imageError = "This feild is required";
			formError = true;
			request.setAttribute("imageError", imageError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleNo.isEmpty()) {
			vehicleNoError = "This feild is required";
			formError = true;
			request.setAttribute("vehicleNoError", vehicleNoError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleName.isEmpty()) {
			vehicleNameError = "This feild is required";
			formError = true;
			request.setAttribute("vehicleNameError", vehicleNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(maxPassenger == 0) {
			maxPassengerError = "This feild is required";
			formError = true;
			request.setAttribute("maxPassengerError", maxPassengerError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(pricePerKm == 0) {
			pricePerKmError = "This feild is required";
			formError = true;
			request.setAttribute("pricePerKmError", pricePerKmError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			errorMessage = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}else {
			Vehicle vehicle = new Vehicle(catId, branchId, image, vehicleNo, vehicleName, maxPassenger, pricePerKm);
			try {
				boolean result = services.addVehicle(vehicle);
				if(result) {
					successMessage = "Records saved successfully.";
					
					String path = getServletContext().getRealPath("/images/taxi" + File.separator + image);
					
					InputStream is = part.getInputStream();
					
					uploadImage(is, path);
				}else {
					errorMessage = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("message", message);
			request.setAttribute("vehicle", vehicle);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
	}
	
	private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		int catId = Integer.parseInt(request.getParameter("catId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String vehicleNo = request.getParameter("vehicleNo");
		String vehicleName = request.getParameter("vehicleName");
		int maxPassenger = Integer.parseInt(request.getParameter("maxPassenger"));
		double pricePerKm = Double.parseDouble(request.getParameter("pricePerKm"));
		
		if(catId == 0) {
			catIdError = "This feild is required";
			formError = true;
			request.setAttribute("catIdError", catIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(branchId == 0) {
			branchIdError = "This feild is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleNo.isEmpty()) {
			vehicleNoError = "This feild is required";
			formError = true;
			request.setAttribute("vehicleNoError", vehicleNoError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(vehicleName.isEmpty()) {
			vehicleNameError = "This feild is required";
			formError = true;
			request.setAttribute("vehicleNameError", vehicleNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(maxPassenger == 0) {
			maxPassengerError = "This feild is required";
			formError = true;
			request.setAttribute("maxPassengerError", maxPassengerError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(pricePerKm == 0) {
			pricePerKmError = "This feild is required";
			formError = true;
			request.setAttribute("pricePerKmError", pricePerKmError);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			errorMessage = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}else {
			Vehicle vehicle = new Vehicle(vehicleId, catId, branchId, vehicleNo, vehicleName, maxPassenger, pricePerKm);
			try {
				boolean result = services.editVehicle(vehicle);
				if(result) {
					successMessage = "Record successfully updated.";
				}else {
					errorMessage = "Record not updated successfully.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("message", message);
			request.setAttribute("vehicle", vehicle);
			RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
			rd.forward(request, response);
		}
	}
	
	private void getAllVehicles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		List<Vehicle> vehicleList;
		try {
			vehicleList = services.getVehicles();
			listSize = vehicleList.size();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			vehicleList = new ArrayList<Vehicle>();
		}
		request.setAttribute("message", message);
		request.setAttribute("listSize", listSize);
		request.setAttribute("vehicleList", vehicleList);
		RequestDispatcher rd = request.getRequestDispatcher("vehicle-list.jsp");
		rd.forward(request, response);
	}
	
	private void getSelectedVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		clearMessage();
		
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		Vehicle vehicle = new Vehicle();
		List<Vehicle> categoryList;
		List<Vehicle> branchList;
		try {
			vehicle = services.getVehicle(vehicleId);
			categoryList = services.categories();
			branchList = services.branches();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			vehicle = new Vehicle();
			categoryList = new ArrayList<Vehicle>();
			branchList = new ArrayList<Vehicle>();
		}
		RequestDispatcher rd = request.getRequestDispatcher("add-vehicle.jsp");
		request.setAttribute("vehicleId", vehicleId);
		request.setAttribute("vehicle", vehicle);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("branchList", branchList);
		rd.forward(request, response);
	}
	
	private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		
		int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
		Vehicle vehicle = new Vehicle();
		try {
			boolean result = services.deleteVehicle(vehicleId);
			if(result) {
				message = "Record deleted successfully";
			}else {
				message = "Record not delete successfull";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("message", message);
		request.setAttribute("vehicle", vehicle);
		response.sendRedirect("vehicle");
	}
	
	private void getCategories(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		
		List<Vehicle> categoryList;
		List<Vehicle> branchList;
		try {
			categoryList = services.categories();
			branchList = services.branches();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			categoryList = new ArrayList<Vehicle>();
			branchList = new ArrayList<Vehicle>();
		}
		HttpSession categorySession = request.getSession();
		categorySession.setAttribute("categoryList", categoryList);
		categorySession.setAttribute("branchList", branchList);
		response.sendRedirect("add-vehicle.jsp");
		
	}
	
	public void uploadImage(InputStream is, String path) {
		try {

			File targetFolder = new File(path);
			Files.copy(is, targetFolder.toPath());
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void clearMessage() {
		message = "";
		successMessage = "";
		errorMessage = "";
		catIdError = "";
		branchIdError = "";
		imageError = "";
		vehicleNoError = "";
		vehicleNameError = "";
		maxPassengerError = "";
		pricePerKmError = "";
	}
	

}
