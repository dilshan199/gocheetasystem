package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Location;

public interface LocationManager {
	public boolean addLocation(Location location) throws ClassNotFoundException, SQLException;
	public boolean editLocation(Location location) throws ClassNotFoundException, SQLException;
	public Location getLocation(int locationId) throws ClassNotFoundException, SQLException;
	public List<Location> getLocations() throws ClassNotFoundException, SQLException;
	public boolean deleteLocation(int locationId) throws ClassNotFoundException, SQLException;
	public List<Location> branches() throws ClassNotFoundException, SQLException;
}
