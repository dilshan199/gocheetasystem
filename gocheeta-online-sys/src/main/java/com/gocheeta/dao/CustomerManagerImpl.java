package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Customer;

public class CustomerManagerImpl implements CustomerManager {
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}
	
	@Override
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM customer";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		List<Customer> customerList = new ArrayList<Customer>();
		while(rst.next()) {
			Customer customer = new Customer();
			customer.setcId(rst.getInt("cId"));
			customer.setFullName(rst.getString("fullName"));
			customer.setContactNo(rst.getString("contactNo"));
			customer.setEmail(rst.getString("email"));
			
			customerList.add(customer);
		}
		stmt.close();
		conn.close();
		
		return customerList;
	}

}
