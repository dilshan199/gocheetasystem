package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.LocationFactory;
import com.gocheeta.dao.LocationManager;
import com.gocheeta.model.Location;

@SuppressWarnings("unused")
public class LocationServices {
	
	private static LocationServices servicesObj;

	private LocationServices() {
		
	}
	
	public static synchronized LocationServices getServicesIns() {
		if(servicesObj == null) {
			servicesObj = new LocationServices();
		}
		
		return servicesObj;
	}
	
	//===================CRUD OPERATION =======================//
	
	//Add rows
	public boolean addLocation(Location location) throws ClassNotFoundException, SQLException {
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.addLocation(location);
	}
	
	//Edit rows
	public boolean editLocation(Location location) throws ClassNotFoundException, SQLException {
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.editLocation(location);
	}
	
	//Get selected row
	public Location getLocation(int locationId) throws ClassNotFoundException, SQLException {
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.getLocation(locationId);
	}
	
	//Get all rows
	public List<Location> getLocations() throws ClassNotFoundException, SQLException{
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.getLocations();
	}
	
	//Delete row
	public boolean deleteLocation(int locationId) throws ClassNotFoundException, SQLException {
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.deleteLocation(locationId);
	}
	
	//Get branch row from branch table
	public List<Location> branches() throws ClassNotFoundException, SQLException{
		LocationManager lm = LocationFactory.locationMangerIns();
		
		return lm.branches();
	}

}
