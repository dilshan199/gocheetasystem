package com.gocheeta.dao;

public class LocationFactory {

	public static LocationManager locationMangerIns() {
		return new LocationManagerImpl();
	}

}
