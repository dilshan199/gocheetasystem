package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gocheeta.model.Category;

@SuppressWarnings("unused")
public class CategoryManagerImpl implements CategoryManager {
	
	//Database connection
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		DbController sqlConnector = new MySqlDbController();
		return sqlConnector.getConnection();
	}

	public boolean addCategory(Category category) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		String sql = "INSERT INTO category(category) VALUES (?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, category.getCategory());
		
		int result = stmt.executeUpdate();
		
		stmt.close();
		conn.close();
		
		return result > 0;
	}

	public Category getCategory(int catId) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM category WHERE catId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, catId);
		ResultSet rst = stmt.executeQuery();
		Category category = new Category();
		
		while(rst.next()) {
			category.setCatId(rst.getInt("catId"));
			category.setCategory(rst.getString("category"));
		}
		
		stmt.close();
		conn.close();
		return category;
	}

	public List<Category> getCategories() throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		
		String sql = "SELECT * FROM category";
		Statement stmt = conn.createStatement();
		ResultSet rst = stmt.executeQuery(sql);
		
		List<Category> categoryList = new ArrayList<Category>();
		
		while(rst.next()) {
			Category category = new Category();
			
			category.setCatId(rst.getInt("catId"));
			category.setCategory(rst.getString("category"));
			
			categoryList.add(category);
		}
		
		return categoryList;
	}

	public boolean editCategory(Category category) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		
		String sql = "UPDATE category SET category = ? WHERE catId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, category.getCategory());
		ps.setInt(2, category.getCatId());
		
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		
		return result > 0;
	}

	public boolean deleteCategory(int catId) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		
		String sql = "DELETE FROM category WHERE catId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, catId);
		int result = ps.executeUpdate();
		
		ps.close();
		conn.close();
		
		return result > 0;
	}

}
