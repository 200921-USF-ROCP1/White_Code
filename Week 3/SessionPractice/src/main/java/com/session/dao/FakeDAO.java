package com.session.dao;

import com.session.model.User;

public class FakeDAO {
	public User login(String username, String password) {
		String cUsername = "jndeer05";
		String cPassword = "1234";
		if (username.equals(cUsername) && password.equals(cPassword) ) {
			User user = new User(1, cUsername, cPassword, "John", "Deer", "jdeerhuntn@gmail.com");
			return user;
		} else return null;
	}
}
