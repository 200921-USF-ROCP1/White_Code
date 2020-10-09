package com.banking.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.banking.models.User;

public class SessionManager {
	private static HttpSession session;
	
	public void createSession(HttpServletRequest request) {
		session = request.getSession();
	}
	
	public void addUser(User user) {
		user.setPassword(null); //hide password
		session.setAttribute("currentUser", user);
	}
	
	public User getUser() {
		return (User) session.getAttribute("currentUser");
	}
	
	public void closeSession() {
		session.invalidate();
	}
}
