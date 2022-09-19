package com.gocheeta.dao;

public class CategoryManagerFactory {
	public static CategoryManager getCategoryManagerIns() {
		return new CategoryManagerImpl();
	}
}
