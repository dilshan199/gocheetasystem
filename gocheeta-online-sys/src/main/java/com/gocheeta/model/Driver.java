package com.gocheeta.model;

public class Driver {
	
	private int driverId;
	private int branchId;
	private int vehicleId;
	private String firstName;
	private String lastName;
	private String nic;
	private String contactNo;
	protected String password;
	private int driverStatus;
	private String branchName;
	private String vehicleNo;
	
	public Driver() {
		
	}

	public Driver(int branchId, int vehicleId, String firstName, String lastName, String nic, String contactNo, String password) {
		
		this.branchId = branchId;
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
		this.contactNo = contactNo;
		this.password = password;
	}

	public Driver(int driverId, int branchId, int vehicleId, String firstName, String lastName, String nic,String contactNo) {
		
		this.driverId = driverId;
		this.branchId = branchId;
		this.vehicleId = vehicleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nic = nic;
		this.contactNo = contactNo;
	}
	
	public Driver(String nic) {
	
		this.nic = nic;
	}

	public Driver(int vehicleId) {
		
		this.vehicleId = vehicleId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(int driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
	

}
