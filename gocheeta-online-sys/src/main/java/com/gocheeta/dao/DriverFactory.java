package com.gocheeta.dao;

public class DriverFactory {

	public static DriverManager getDriverManagerIns() {
		return new DriverManagerImpl();
	}

}
