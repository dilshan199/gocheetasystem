package com.gocheeta.model;

public class Booking {

	private int bookingId;
	private int branchId;
	private int vehicleId;
	private int driverId;
	private int cId;
	private String refferanceNo;
	private String pickUp;
	private String drop;
	private String pickUpDate;
	private double price;
	private int bookingStatus;
	private String bookingDate;
	private String startDate;
	private String endDate;
	private String vehicleNo;
	private String branchName;
	private String firstName;
	private String lastName;
	private String fullName;
	
	public Booking() {
		
	}

	public Booking(int branchId, String startDate, String endDate) {
		
		this.branchId = branchId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Booking(int bookingId, int branchId, int vehicleId,int driverId, int cId, String refferanceNo, String pickUp, String drop,
			String pickUpDate, double price, int bookingStatus, String bookingDate) {
		
		this.bookingId = bookingId;
		this.branchId = branchId;
		this.vehicleId = vehicleId;
		this.driverId = driverId;
		this.cId = cId;
		this.refferanceNo = refferanceNo;
		this.pickUp = pickUp;
		this.drop = drop;
		this.pickUpDate = pickUpDate;
		this.price = price;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getRefferanceNo() {
		return refferanceNo;
	}

	public void setRefferanceNo(String refferanceNo) {
		this.refferanceNo = refferanceNo;
	}

	public String getPickUp() {
		return pickUp;
	}

	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}

	public String getDrop() {
		return drop;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public String getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
}
