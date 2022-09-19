package com.gocheeta.dao;

import java.sql.SQLException;
import java.util.List;

import com.gocheeta.model.User;

public interface UserManager {
	public boolean addUser(User user) throws ClassNotFoundException, SQLException;
	public boolean editUser(User user) throws ClassNotFoundException, SQLException;
	public List<User> getUsers() throws ClassNotFoundException, SQLException;
	public User getUser(int userId) throws ClassNotFoundException, SQLException;
	public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException;
	public List<User> getBranches() throws ClassNotFoundException, SQLException;
	public boolean checkUserExit(String userName) throws ClassNotFoundException, SQLException;
}
