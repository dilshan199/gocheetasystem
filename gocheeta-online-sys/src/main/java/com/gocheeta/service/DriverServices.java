package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.DriverFactory;
import com.gocheeta.dao.DriverManager;
import com.gocheeta.model.Driver;

@SuppressWarnings("unused")
public class DriverServices {

	private static DriverServices driverServicesObj;
	
	private DriverServices() {
		
	}
	
	public static synchronized DriverServices servicesIns() {
		if(driverServicesObj == null) {
			driverServicesObj = new DriverServices();
		}
		
		return driverServicesObj;
	}
	
	//=================== CRUD OPERATION ==============//
	
	//Add driver
	public boolean addDriver(Driver driver) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.addDriver(driver);
	}
	
	//Edit driver
	public boolean editDriver(Driver driver) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.editDriver(driver);
	}
	
	//Get all drivers
	public List<Driver> getDrivers() throws ClassNotFoundException, SQLException{
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.getDrivers();
	}
	
	//Get selected driver
	public Driver getDriver(int driverId) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.getDriver(driverId);
	}
	
	//Delete driver
	public boolean deleteDriver(int driverId) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.deleteDriver(driverId);
	}
	
	//Assign vehicle
	public boolean exitVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.exitVehicle(vehicleId);
	}
	
	//Check driver already exit
	public boolean exitDriver(String nic) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.exitDriver(nic);
	}
	
	public List<Driver> branches() throws ClassNotFoundException, SQLException{
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.branches();
	}
	
	public List<Driver> vehicles() throws ClassNotFoundException, SQLException{
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.vehicles();
	}
	
	public Driver getDriverProfile(int driverId) throws ClassNotFoundException, SQLException {
		DriverManager driverManager = DriverFactory.getDriverManagerIns();
		
		return driverManager.getDriverProfile(driverId);
	}

}
