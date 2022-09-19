package com.gocheeta.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gocheeta.model.Login;
import com.gocheeta.service.LoginServices;

public class LoginController extends HttpServlet {
	
	LoginServices services;
	String message, userNameError, passwordError, firstLetterUppercase, upperUserName;
	boolean formError = false;
	private static final long serialVersionUID = 1L;
    
    public LoginController() {
       services = LoginServices.servicesIns();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/logout":
			logout(request, response);
			break;
			default:
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type.equals("submit")) {
			try {
				login(request, response);
			} catch (NoSuchAlgorithmException | ServletException | IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		clearMessage();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if(userName.isEmpty()) {
			userNameError = "This feild is required";
			formError = true;
			request.setAttribute("userNameError", userNameError);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			Login checkUser = new Login();
			try {
				
				boolean userFound = services.exitUser(userName);
				if(!userFound) {
					userNameError = "This user not exit";
					formError = true;
					request.setAttribute("userNameError", userNameError);
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			
		}
		
		if(password.isEmpty()) {
			passwordError = "This feild is required";
			formError = true;
			request.setAttribute("passwordError", passwordError);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			if(password.length() != 10) {
				passwordError = "Password include 10 digits";
				formError = true;
				request.setAttribute("passwordError", passwordError);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}else {
				password = encryptPassword(password);
				
				Login checkPassword = new Login();
				try {
					boolean validPassword = services.checkPassword(userName, password);
					if(!validPassword) {
						passwordError = "Invalid password";
						formError = true;
						request.setAttribute("passwordError", passwordError);
						RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
						rd.forward(request, response);
					}
				} catch (ClassNotFoundException | SQLException e) {
					message = e.getMessage();
				}
				
			}
		}
		
		if(formError) {
			message = "Login Failed";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			Login login = new Login();
			try {
				
				login = services.getUser(password);
				String fun = login.getUserName();
				firstLetterUppercase = fun.substring(0, 1).toUpperCase();
				upperUserName = fun.substring(0, 1).toUpperCase() + fun.substring(1);
				
			} catch (ClassNotFoundException | SQLException e) {
				login = new Login();
			}
			HttpSession loggedin = request.getSession();
			loggedin.setAttribute("login", login);
			loggedin.setAttribute("firstLetterUppercase", firstLetterUppercase);
			loggedin.setAttribute("upperUserName", upperUserName);
			response.sendRedirect("/gocheeta-online-sys");
		}
	}
	
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
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession loggedin = request.getSession(false);
		loggedin.invalidate();
		response.sendRedirect("login");
	}
	
	public void clearMessage() {
		message = "";
	}

}
