package com.gocheeta.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gocheeta.model.User;
import com.gocheeta.service.UserServices;

public class UserController extends HttpServlet {
	
	UserServices services;
	String message, userNameError, userTypeError, passwordError, successMessage, errorMessage, branchIdError, cPError, encryptPassword;
	int listSize;
	boolean formError;
	private static final long serialVersionUID = 1L;
    
    public UserController() {
        services = UserServices.servicesInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
		case "/userEdit":
			getSelectedUser(request, response);
			break;
		case "/userDelete":
			deleteUser(request, response);
			break;
		case "/new":
			getBranches(request, response);
			break;
			default:
				getAllUsers(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type != null) {
			if(type.equals("submit")) {
				try {
					addUser(request, response);
				} catch (NoSuchAlgorithmException | ServletException | IOException e) {
					//e.printStackTrace();
				}
			}else {
				updateUser(request, response);
			}
		}
	}
	
	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		List<User> userList;
		try {
			userList = services.getUsers();
			listSize = userList.size();
			if(listSize == 0) {
				message = "Records not found!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			userList = new  ArrayList<User>();
		}
		RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
		request.setAttribute("message", message);
		request.setAttribute("userList", userList);
		request.setAttribute("listSize", listSize);
		rd.forward(request, response);
	}
	
	private void getBranches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		List<User> branchList;
		try {
			branchList = services.getBranches();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			branchList = new ArrayList<User>();
		}
		HttpSession branchSession = request.getSession();
		branchSession.setAttribute("branchList", branchList);
		response.sendRedirect("add-user.jsp");
		
	}
	
	private void getSelectedUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		clearMessage();
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = new User();
		try {
			user = services.getUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			user = new User();
		}
		RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
		request.setAttribute("userId", userId);
		request.setAttribute("user", user);
		rd.forward(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = new User();
		try {
			boolean result = services.deleteUser(userId);
			if(result) {
				message = "Record deleted successfully";
			}else {
				message = "Record not delete successfull";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("message", message);
		request.setAttribute("user", user);
		response.sendRedirect("user");
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		clearMessage();
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String userName = request.getParameter("userName");
		String userType = request.getParameter("userType");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		int userStatus = Integer.parseInt(request.getParameter("userStatus"));
		int intPassword = Integer.parseInt(password);
		int intCPassword = Integer.parseInt(confirmPassword);
		
		if(branchId == 0) {
			branchIdError = "This field is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		if(userName.isEmpty()) {
			userNameError = "This field is required";
			formError = true;
			request.setAttribute("userNameError", userNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		if(userType.isEmpty()) {
			userTypeError = "This field is required";
			formError = true;
			request.setAttribute("userTypeError", userTypeError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		if(password.isEmpty()) {
			passwordError = "This field is required";
			formError = true;
			request.setAttribute("passwordError", passwordError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}else {
			if(password.length() != 10) {
				passwordError = "Password must include 10 digits";
				formError = true;
				request.setAttribute("passwordError", passwordError);
				RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
				rd.forward(request, response);
			}
		}
		if(confirmPassword.isEmpty()) {
			cPError = "This field is required";
			formError = true;
			request.setAttribute("cPError", cPError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}else if(confirmPassword.length() != 10) {
			cPError = "Password must include 10 digits";
			formError = true;
			request.setAttribute("cPError", cPError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}else {
			if(intPassword != intCPassword) {
				cPError = "Password did\'t match";
				formError = true;
				request.setAttribute("cPError", cPError);
				RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
				rd.forward(request, response);
			}
		}
		
		password = encryptPassword(password);
		
		if(formError == true) {
			errorMessage = "Record can\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}else {
			User user = new User(branchId, userType, userName, password, userStatus);
			try {
				boolean result = services.addUser(user);
				if(result) {
					successMessage = "Records saved successfully.";
				}else {
					errorMessage = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("message", message);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String userType = request.getParameter("userType");
		String userName = request.getParameter("userName");
		
		if(branchId == 0) {
			branchIdError = "This field is required";
			formError = true;
			request.setAttribute("branchIdError", branchIdError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		if(userName.isEmpty()) {
			userNameError = "This field is required";
			formError = true;
			request.setAttribute("userNameError", userNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		if(userType.isEmpty()) {
			userTypeError = "This field is required";
			formError = true;
			request.setAttribute("userTypeError", userTypeError);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			errorMessage = "Record can\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}else {
			User user = new User(userId, branchId, userType, userName);
			try {
				boolean result = services.editUser(user);
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
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
			rd.forward(request, response);
		}
		
	}
	
	public void clearMessage() {
		message = "";
		branchIdError = "";
		userNameError = "";
		passwordError = "";
		successMessage = "";
		errorMessage = "";
		userTypeError = "";
		cPError = "";
	}
	
	//Encrypt password
	public String encryptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] bytesValue = md.digest();
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < bytesValue.length;i++) {
			sb.append(Integer.toString((bytesValue[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
