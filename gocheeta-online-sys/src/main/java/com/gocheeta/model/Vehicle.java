package com.gocheeta.model;

public class Vehicle {

	private int vehicleId;
	private int catId;
	private int branchId;
	private String image;
	private String vehicleNo;
	private String vehicleName;
	private int maxPassenger;
	private double pricePerKm;
	private String category;
	private String branchName;
	private int vehicleStatus;
	
	public Vehicle() {
		
	}

	public Vehicle(int catId, int branchId, String image, String vehicleNo, String vehicleName, int maxPassenger, double pricePerKm) {
		
		this.catId = catId;
		this.branchId = branchId;
		this.image = image;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.maxPassenger = maxPassenger;
		this.pricePerKm = pricePerKm;
	}
	
	

	public Vehicle(int vehicleId, int catId, int branchId, String vehicleNo, String vehicleName, int maxPassenger, double pricePerKm) {
		
		this.vehicleId = vehicleId;
		this.catId = catId;
		this.branchId = branchId;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.maxPassenger = maxPassenger;
		this.pricePerKm = pricePerKm;
	}

	public Vehicle(int vehicleId, int catId, int branchId, String image, String vehicleNo, String vehicleName, int maxPassenger, double pricePerKm) {
	
		this.vehicleId = vehicleId;
		this.catId = catId;
		this.branchId = branchId;
		this.image = image;
		this.vehicleNo = vehicleNo;
		this.vehicleName = vehicleName;
		this.maxPassenger = maxPassenger;
		this.pricePerKm = pricePerKm;
	}

	public Vehicle(int catId, String category) {
		
		this.catId = catId;
		this.category = category;
	}

	public Vehicle(String vehicleNo) {
		
		this.vehicleNo = vehicleNo;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getMaxPassenger() {
		return maxPassenger;
	}

	public void setMaxPassenger(int maxPassenger) {
		this.maxPassenger = maxPassenger;
	}

	public double getPricePerKm() {
		return pricePerKm;
	}

	public void setPricePerKm(double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getVehicleStatus() {
		return vehicleStatus;
	}
	
	public void setVehicleStatus(int vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
}
