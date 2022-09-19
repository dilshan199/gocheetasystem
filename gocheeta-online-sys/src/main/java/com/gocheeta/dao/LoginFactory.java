package com.gocheeta.dao;

public class LoginFactory {

	public static LoginManager getLoginIns() {
		return new LoginManagerImpl();
	}

}
