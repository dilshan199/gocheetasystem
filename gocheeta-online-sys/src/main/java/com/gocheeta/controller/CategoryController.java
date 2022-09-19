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

import com.gocheeta.model.Category;
import com.gocheeta.service.CategoryService;

public class CategoryController extends HttpServlet {
	
	CategoryService services;
	String message, categoryError, successMessage, sessionMessage;
	int listSize;
	boolean formError;
	private static final long serialVersionUID = 1L;
    
    public CategoryController() {
        services = CategoryService.getCategoryServicesInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
		case "/edit":
			getSelectedCategory(request, response);
			break;
		case "/delete":
			deleteCategory(request, response);
			break;
			default:
				getAllCategories(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type != null) {
			if(type.equals("submit")) {
				addCategory(request, response);
			}else {
				updateCategory(request, response);
			}
		}
	}
	
	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		String categoryName = request.getParameter("category");
		
		if(categoryName.isEmpty()) {
			categoryError = "This field is required";
			formError = true;
			request.setAttribute("categoryError", categoryError);
			RequestDispatcher rd = request.getRequestDispatcher("add-category.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			message = "Record can\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
		}else {
			Category category = new Category(categoryName);
			try {
				boolean result = services.addCategory(category);
				if(result) {
					successMessage =  "Records saved successfully.";
				}else {
					message = "Records save unsuccessfull.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			request.setAttribute("category",category);
			RequestDispatcher rd = request.getRequestDispatcher("add-category.jsp");
			rd.forward(request, response);
		}
	}
	
	private void getAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		List<Category> categoryList;
		try {
			categoryList = services.getCategories();
			listSize = categoryList.size();
			if(listSize == 0) {
				message = "Records not found!";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			categoryList = new ArrayList<Category>();
		}
		request.setAttribute("message", message);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("listSize", listSize);
		RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
		rd.forward(request, response);
	}
	
	private void getSelectedCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		clearMessage();
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		Category category = new Category();
		try {
			category = services.getCategory(catId);
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
			category = new Category();
		}
		
		HttpSession categorySession = request.getSession();
		categorySession.setAttribute("category", category);
		response.sendRedirect("update-category.jsp");
	}
	
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int catId = Integer.parseInt(request.getParameter("catId"));
		Category category = new Category();
		try {
			boolean result = services.deleteCategory(catId);
			if(result) {
				message = "Record is deleted.";
			}else {
				message = "Record can't delete.";
			}
		} catch (ClassNotFoundException | SQLException e) {
			message = e.getMessage();
		}
		request.setAttribute("message", message);
		request.setAttribute("category", category);
		response.sendRedirect("category");
	}
	
	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		clearMessage();
		int catId = Integer.parseInt(request.getParameter("catId"));
		String categoryName = request.getParameter("category");
		if(categoryName.isEmpty()) {
			categoryError = "This feild is required";
			formError = true;
			request.setAttribute("categoryError", categoryError);
			RequestDispatcher rd = request.getRequestDispatcher("update-category.jsp");
			rd.forward(request, response);
		}
		
		if(formError == true) {
			message = "Record can\'t save. Invalid data or missing some required feilds!";
			request.setAttribute("message", message);
		}else {
			Category category = new Category(catId, categoryName);
			try {
				boolean result = services.editCategory(category);
				if(result) {
					successMessage = "Record successfully updated.";
				}else {
					message = "Record not updated successfully.";
				}
			} catch (ClassNotFoundException | SQLException e) {
				message = e.getMessage();
			}
			request.setAttribute("category", category);
			request.setAttribute("successMessage", successMessage);
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("update-category.jsp");
			rd.forward(request, response);
		}
	}
	
	public void clearMessage() {
		message = "";
		categoryError = "";
		successMessage = "";
	}

}
