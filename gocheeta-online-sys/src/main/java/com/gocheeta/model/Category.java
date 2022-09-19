package com.gocheeta.model;

public class Category {
	
	private int catId;
	private String category;
	
	public Category() {
		
	}
	
	public Category(String category) {
		
		this.category = category;
	}

	public Category(int catId, String category) {
		
		this.catId = catId;
		this.category = category;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
