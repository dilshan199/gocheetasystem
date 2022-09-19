package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.CustomerFactory;
import com.gocheeta.dao.CustomerManager;
import com.gocheeta.model.Customer;

@SuppressWarnings("unused")
public class CustomerServices {
	
	private static CustomerServices customerServicesObj;
	
	private CustomerServices() {
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized CustomerServices customerServicesIns() {
		if(customerServicesObj == null) {
			customerServicesObj = new CustomerServices();
		}
		
		return customerServicesObj;
	}
	
	//======================== CRUD OPERATION ==========================//
	
	//Select all rows
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException{
		CustomerManager cm = CustomerFactory.getCustomerIns();
		
		return cm.getCustomers();
	}

}
