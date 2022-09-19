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

import com.gocheeta.model.Customer;
import com.gocheeta.service.CustomerServices;

public class CustomerController extends HttpServlet {
	
	CustomerServices services;
	int listSize;
	private static final long serialVersionUID = 1L;
    
    public CustomerController() {
        services = CustomerServices.customerServicesIns();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch(action) {
		case "/bookinglist":
			break;
			default:
				allCustomers(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void allCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Customer> customerList;
		try {
			
			customerList = services.getCustomers();
			listSize = customerList.size();
			
		} catch (ClassNotFoundException | SQLException e) {
			customerList = new ArrayList<Customer>();
		}
		request.setAttribute("customerList", customerList);
		request.setAttribute("listSize", listSize);
		RequestDispatcher rd = request.getRequestDispatcher("customer-list.jsp");
		rd.forward(request, response);
	}
}
