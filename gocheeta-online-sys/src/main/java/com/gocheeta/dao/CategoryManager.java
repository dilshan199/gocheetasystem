package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.Category;

public interface CategoryManager {

	public boolean addCategory(Category category) throws ClassNotFoundException, SQLException;
	public Category getCategory(int catId) throws ClassNotFoundException, SQLException;
	public List<Category> getCategories() throws ClassNotFoundException, SQLException;
	public boolean editCategory(Category category) throws ClassNotFoundException, SQLException;
	public boolean deleteCategory(int catId) throws ClassNotFoundException, SQLException;
}
