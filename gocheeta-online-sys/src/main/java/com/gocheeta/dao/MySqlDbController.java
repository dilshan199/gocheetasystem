package com.gocheeta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbController implements DbController{

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/gocheeta";
		String user = "root";
		String password = "Cis@1990C";
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}
