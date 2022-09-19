package com.gocheeta.dao;

public class VehicleFactory {
	public static VehicleManager vehicleManagerIns() {
		return new VehicleManagerImpl();
	}
}
