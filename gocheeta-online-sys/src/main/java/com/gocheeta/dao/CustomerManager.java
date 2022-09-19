package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Customer;

public interface CustomerManager {
	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException;
}
