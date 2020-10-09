package com.session.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.session.model.User;



public class SessionManager {
	private static HttpSession session;
	
	public static void createSession(HttpServletRequest request) {
		session = request.getSession();
	}
	
	public static void addUser(User user) {
		user.setPassword(null); //hide password
		session.setAttribute("currentUser", user);
	}
	
	public static User getUser() {
		return (User) session.getAttribute("currentUser");
	}
	
	public static void closeSession() {
		session.invalidate();
	}
}
