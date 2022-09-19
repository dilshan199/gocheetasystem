package com.gocheeta.dao;

public class CustomerFactory {
	public static CustomerManager getCustomerIns() {
		return new CustomerManagerImpl();
	}
}
