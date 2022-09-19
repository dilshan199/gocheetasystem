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

import com.gocheeta.model.Branch;
import com.gocheeta.service.BranchServices;

public class BranchController extends HttpServlet {
	
	BranchServices services;
	String  message, successMessage, branchNameError, sessionMessage;
	boolean formError;
	int listSize;
	private static final long serialVersionUID = 1L;
   
    public BranchController() {
        services = BranchServices.servicesInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
		case "/branchEdit":
			getSelectedBranch(request, response);
			break;
		case "/branchDelete":
			deleteBranch(request, response);
			break;
			default:
				getAllBranches(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type != null) {
			if(type.equals("submit")) {
				addBranch(request, response);
			}else {
				updateBranch(request, response);
			}
		}
	}
	
	private void getAllBranches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		
		List<Branch> branchList;
		try {
			branchList = services.getBranchs();
			listSize = branchList.size();
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			branchList = new ArrayList<Branch>();
		}
		request.setAttribute("message", message);
		request.setAttribute("listSize", listSize);
		request.setAttribute("branchList", branchList);
		RequestDispatcher rd = request.getRequestDispatcher("branch-list.jsp");
		rd.forward(request, response);
	}
	 
	private void getSelectedBranch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		Branch branch = new Branch();
		try {
			branch = services.getBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			branch = new Branch();
		}
		
		HttpSession branchSession = request.getSession();
		branchSession.setAttribute("branch", branch);
		response.sendRedirect("update-branch.jsp");
	}
	
	private void deleteBranch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		clearMessage();
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		Branch branch = new Branch();
		try {
			boolean result = services.deleteBranch(branchId);
			if(result) {
				message = "Record deleted successfully.";
			}else {
				message = "Record can\'t delete";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("message", message);
		request.setAttribute("branch", branch);
		response.sendRedirect("branch");
	}
	
	private void addBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		String branchName = request.getParameter("branchName");
		int branchStatus = Integer.parseInt(request.getParameter("branchStatus"));
		if(branchName.isEmpty()) {
			branchNameError = "This feild is required";
			formError = true;
			request.setAttribute("branchNameError", branchNameError);
			RequestDispatcher rd = request.getRequestDispatcher("add-branch.jsp");
			rd.forward(request, response);
		}
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
		}else {
			Branch branch = new Branch(branchName, branchStatus);
			try {
				boolean result = services.addBranch(branch);
				if(result) {
					successMessage = "Records saved successfully.";
				}else {
					message = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("message", message);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("branch", branch);
			RequestDispatcher rd = request.getRequestDispatcher("add-branch.jsp");
			rd.forward(request, response);
		}
	}
	
	private void updateBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String branchName = request.getParameter("branchName");
		int branchStatus = Integer.parseInt(request.getParameter("branchStatus"));
		if(branchName.isEmpty()) {
			branchNameError = "This feild is required";
			formError = true;
			request.setAttribute("branchNameError", branchNameError);
			RequestDispatcher rd = request.getRequestDispatcher("update-branch.jsp");
			rd.forward(request, response);
		}
		if(formError == true) {
			message = "Record can\\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
		}else {
			Branch branch = new Branch(branchId, branchName, branchStatus);
			try {
				boolean result = services.editBranch(branch);
				if(result) {
					successMessage = "Record successfully updated.";
				}else {
					message = "Record not updated successfully.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("branchNameError", branchNameError);
			request.setAttribute("message", message);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("branch", branch);
			RequestDispatcher rd = request.getRequestDispatcher("update-branch.jsp");
			rd.forward(request, response);
		}
	}
	
	public void clearMessage() {
		message = "";
		successMessage = "";
		branchNameError = "";
	}

}
