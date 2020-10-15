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
import com.fasterxml.jackson.databind.JsonNode;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JsonInterp<User> interp = new JsonInterp<User>(User.class);
	
	private static UserService uServ = new UserServiceImpl();
	private static SessionManager sm = new SessionManager();
	
	
	private User user;
	private List<User> users;
	private JsonNode node;
	private String jsonString = null;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
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
					if (user != null) jsonString = interp.marshal(user); //check if user was found
					else response.sendError(404, "User not found");
					
				} else response.sendError(401, "You do not have access to this user.");
			} else response.sendError(400);
		} else {
			// -- lookup all users -- 
			if (Authentication.canAccess("Employee")) {
				users = uServ.getAllUsers();
				//return users
				jsonString = interp.marshal(users);
			} else response.sendError(401, "Employee access only.");
		}
		
		//return output
		if (jsonString != null) {
			response.getWriter().println(jsonString); //send json string
			jsonString = null;
		}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}
		
		String path = request.getRequestURI(); // "BankingAPI/user/upgrade
		String[] parts = path.split("/"); // ["","BankingAPI","user","upgrade"]

		if (parts.length == 4) { // -- upgrade user to premium -- 
			node = interp.getNode(request);
			
			int userId = node.get("userId").asInt();
			int accId = node.get("accountId").asInt();
			
			user = uServ.getUserById(userId);
			if (user.getRole().getRole().equals("Standard")) { //only standard users can upgrade
				user = uServ.upgradeToPremium(user, accId); // upgrade
				response.getWriter().println(interp.marshal(user)); //return user
			} else response.sendError(400,"Only 'Standard' users can upgrade");
			
		} else { // -- Update user info -- 
			if (Authentication.canAccess("Admin")) {
				//get user from json
				user = interp.unmarshal(request);
				user.setRole(user.roleId);
				//update user
				user = uServ.updateUser(user);
				//send response
				jsonString = interp.marshal(user);
				response.getWriter().println(jsonString);
			} else response.sendError(401, "Only an admin can update user information.");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}
		
		String path = request.getRequestURI(); // "BankingAPI/user/remove/1
		String[] parts = path.split("/"); // ["","BankingAPI","user","remove","1"]
		
		if (parts.length == 5) { //correct input length
			if(StringUtils.isInteger(parts[4])) { //parse uri
				int userId = Integer.parseInt(parts[4]);
				
				// authenticate logged-in user
				if (Authentication.canAccess("Admin")) {
					
					//check if account exists
					user = uServ.getUserById(userId);
					if (user == null) {
						response.sendError(404, "User not found.");
						return;
					}
					
					if (uServ.deleteUser(userId)) { //user deleted successfully
						response.getWriter().println(String.format("User %d deleted successfully.", userId));
					} else response.sendError(400, "Deletion failed.");
					
				} else response.sendError(401,"Only an admin can delete accounts.");
			}
		} else response.sendError(400,"Please specify a user id.");
	}
}
