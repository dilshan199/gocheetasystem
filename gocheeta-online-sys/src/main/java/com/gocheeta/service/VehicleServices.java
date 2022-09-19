package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.VehicleFactory;
import com.gocheeta.dao.VehicleManager;
import com.gocheeta.model.Vehicle;

@SuppressWarnings("unused")
public class VehicleServices {

	private static VehicleServices vehicleServicesObj;
	
	private VehicleServices() {
		
	}
	
	public static synchronized VehicleServices servicesInstance() {
		if(vehicleServicesObj == null) {
			vehicleServicesObj = new VehicleServices();
		}
		
		return vehicleServicesObj;
	}
	
	//======================= CRUD OPERATION =======================//
	
	//Add vehicle
	public boolean addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.addVehicle(vehicle);
	}
	
	//Edit vehicle
	public boolean editVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException {
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.editVehicle(vehicle);
	}
	
	//Get selected vehicle
	public Vehicle getVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.getVehicle(vehicleId);
	}
	
	//Get all vehicle
	public List<Vehicle> getVehicles() throws ClassNotFoundException, SQLException{
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.getVehicles();
	}
	
	//Delete vehicle
	public boolean deleteVehicle(int vehicleId) throws ClassNotFoundException, SQLException {
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.deleteVehicle(vehicleId);
	}
	
	//Get all categories
	public List<Vehicle> categories() throws ClassNotFoundException, SQLException{
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.categories();
	}
	
	//Get all branches
	public List<Vehicle> branches() throws ClassNotFoundException, SQLException{
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.branches();
	}
	
	//Check exit vehicle
	public boolean exitVehicle(String vehicleNo) throws ClassNotFoundException, SQLException {
		VehicleManager vehicleManager = VehicleFactory.vehicleManagerIns();
		
		return vehicleManager.exitVehicle(vehicleNo);
	}

}
