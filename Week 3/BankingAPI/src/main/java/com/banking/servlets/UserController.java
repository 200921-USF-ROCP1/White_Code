package com.banking.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.interfaces.AccountService;
import com.banking.interfaces.UserService;
import com.banking.models.Account;
import com.banking.models.User;
import com.banking.services.AccountServiceImpl;
import com.banking.services.UserServiceImpl;
import com.banking.utils.Authentication;
import com.banking.utils.JsonInterp;
import com.banking.utils.SessionManager;
import com.banking.utils.StringUtils;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JsonInterp<User> interp = new JsonInterp<User>(User.class);
	
	private static UserService uServ = new UserServiceImpl();
	private static SessionManager sm = new SessionManager();
	
	private User user;
	private List<User> users;
	private String jsonString = null;
	private int errorCode = 400;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403);
			return;
		}
		
		String path = request.getRequestURI(); // "BankingAPI/user/1
		String[] parts = path.split("/"); // ["","BankingAPI","user","1"]
		
		if (parts.length == 4) {
			
			// -- lookup user by userId --
			if(StringUtils.isInteger(parts[3])) { //parse uri
				int userId = Integer.parseInt(parts[3]);
				
				// authenticate logged-in user
				if (Authentication.canAccess("Employee") || sm.getUser().getUserId() == userId ) {
					user = uServ.getUserById(userId);
					// return user
					jsonString = interp.marshal(user);
				} else errorCode = 401;
			}
		} else {
			// -- lookup all users -- 
			if (Authentication.canAccess("Employee")) {
				users = uServ.getAllUsers();
				//return users
				jsonString = interp.marshal(users);
			} else errorCode = 401;
		}
		
		//return output
		if (jsonString != null) {
			response.getWriter().println(jsonString); //send json string
			jsonString = null;
		} else {
			response.sendError(errorCode);
			errorCode = 400;
		}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403);
			return;
		}
		
		// -- Update user --
		
		if (Authentication.canAccess("Admin")) {
			//get user from json
			user = interp.unmarshal(request);
			user.setRole(user.roleId);
			//update user
			user = uServ.updateUser(user);
			//send response
			jsonString = interp.marshal(user);
			response.getWriter().println(jsonString);
		} else response.sendError(401);
	}

}