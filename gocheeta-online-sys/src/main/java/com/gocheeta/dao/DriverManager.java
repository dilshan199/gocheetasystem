package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Driver;

public interface DriverManager {
	public boolean addDriver(Driver driver) throws ClassNotFoundException, SQLException;
	public boolean editDriver(Driver driver) throws ClassNotFoundException, SQLException;
	public List<Driver> getDrivers() throws ClassNotFoundException, SQLException;
	public Driver getDriver(int driverId) throws ClassNotFoundException, SQLException;
	public boolean deleteDriver(int driverId) throws ClassNotFoundException, SQLException;
	public boolean exitVehicle(int vehicleId) throws ClassNotFoundException, SQLException;
	public boolean exitDriver(String nic) throws ClassNotFoundException, SQLException;
	public List<Driver> branches() throws ClassNotFoundException, SQLException;
	public List<Driver> vehicles() throws ClassNotFoundException, SQLException;
	public Driver getDriverProfile(int driverId) throws ClassNotFoundException, SQLException;
}
