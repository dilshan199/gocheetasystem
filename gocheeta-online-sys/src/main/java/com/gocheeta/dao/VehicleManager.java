package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Vehicle;

public interface VehicleManager {
	public boolean addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	public boolean editVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	public Vehicle getVehicle(int vehicleId) throws ClassNotFoundException, SQLException;
	public List<Vehicle> getVehicles() throws ClassNotFoundException, SQLException;
	public boolean deleteVehicle(int vehicleId) throws ClassNotFoundException, SQLException;
	public List<Vehicle> categories() throws ClassNotFoundException, SQLException;
	public List<Vehicle> branches() throws ClassNotFoundException, SQLException;
	public boolean exitVehicle(String vehicleNo) throws ClassNotFoundException, SQLException;
}
