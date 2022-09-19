package com.gocheeta.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.dao.CategoryManager;
import com.gocheeta.dao.CategoryManagerFactory;
import com.gocheeta.model.Category;

@SuppressWarnings("unused")
public class CategoryService {

	private static CategoryService categoryServiceObj;
	
	private CategoryService() {
		
	}
	
	public static synchronized CategoryService getCategoryServicesInstance() {
		if(categoryServiceObj == null) {
			categoryServiceObj = new CategoryService();
		}
		
		return categoryServiceObj;
	}
	
	//----------------CRUD OPERATIONS-------------------//
	//Add category
	public boolean addCategory(Category category) throws ClassNotFoundException, SQLException {
		
		CategoryManager categoryManager = CategoryManagerFactory.getCategoryManagerIns();
		
		return categoryManager.addCategory(category);
	}
	
	//Fetch selected category
	public Category getCategory(int catId) throws ClassNotFoundException, SQLException {
		
		CategoryManager categoryManager = CategoryManagerFactory.getCategoryManagerIns();
		
		return categoryManager.getCategory(catId);
	}
	
	//Fetch list of category
	public List<Category> getCategories() throws ClassNotFoundException, SQLException{
		
		CategoryManager categoryManager = CategoryManagerFactory.getCategoryManagerIns();
		
		return categoryManager.getCategories();
	}
	
	//Update selected row
	public boolean editCategory(Category category) throws ClassNotFoundException, SQLException {
		
		CategoryManager categoryManager = CategoryManagerFactory.getCategoryManagerIns();
		
		return categoryManager.editCategory(category);
	}
	
	//Delete selected row
	public boolean deleteCategory(int catId) throws ClassNotFoundException, SQLException {
		
		CategoryManager categoryManager = CategoryManagerFactory.getCategoryManagerIns();
		
		return categoryManager.deleteCategory(catId);
	}

}
