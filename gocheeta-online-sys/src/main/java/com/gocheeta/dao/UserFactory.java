package com.gocheeta.dao;

public class UserFactory {

	public static UserManager getUserManagerIns() {
		return new UserManagerImpl();
	}

}
