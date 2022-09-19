package com.gocheeta.model;

public class Location {

	private int locationId;
	private int branchId;
	private String street;
	private String city;
	private double distination;
	private String branchName;
	
	public Location() {
		
	}

	public Location(int branchId, String street, String city, double distination) {
		
		this.branchId = branchId;
		this.street = street;
		this.city = city;
		this.distination = distination;
	}

	public Location(int locationId, int branchId, String street, String city, double distination) {
		
		this.locationId = locationId;
		this.branchId = branchId;
		this.street = street;
		this.city = city;
		this.distination = distination;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getDistination() {
		return distination;
	}

	public void setDistination(double distination) {
		this.distination = distination;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

}
