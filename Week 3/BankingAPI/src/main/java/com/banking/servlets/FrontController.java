package com.banking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.interfaces.UserService;
import com.banking.models.User;
import com.banking.services.UserServiceImpl;
import com.banking.utils.Authentication;
import com.banking.utils.JsonInterp;
import com.banking.utils.SessionManager;
import com.fasterxml.jackson.databind.JsonNode;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JsonInterp<User> interp = new JsonInterp<User>(User.class);
	private static UserService uServ = new UserServiceImpl();
	private static User user;
	private static SessionManager sm = new SessionManager();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		
		JsonNode node = interp.getNode(request);
		
		String path = request.getRequestURI(); // "/BankingAPI/login
		String[] parts = path.split("/"); // ["","BankingAPI","login"]
		
		if (parts.length >= 3) {
			switch (parts[2].toLowerCase()) {
			case "login": {
				if (sm.getUser() == null) { //dont allow someone to login if someone is already logged in
					String username = node.get("username").asText();
					String password = node.get("password").asText();
						user = uServ.login(username, password);
						
					if (user != null) { //login successful
						sm.addUser(user);
						response.setStatus(202); //accepted
						System.out.printf("%s %s has logged in.\n\n",user.getFirstName(),user.getLastName());
						response.getWriter().println(interp.marshal(user));
					} else {
						response.getWriter().println("Invalid username and password combination");
					}
					
				} else response.sendError(405); //method not allowed
				break;
			}
			case "logout": {
				if (sm.getUser() == null) //no user logged in
					response.sendError(403);
				else {
					sm.closeSession();
					response.setStatus(200);
				}
				break;
			}
			case "register": {
				if (sm.getUser() == null) { //check if logged in 
					response.sendError(403);
					break;
				}
				if (Authentication.canAccess("Admin")) { //must be admin
					user = interp.unmarshal(request); //get user object from body
					user.setRole(user.roleId);
					uServ.register(user);
				} response.sendError(401);
				break;
			}
			}
			
		} else { //no argument after host '/BankingAPI'
			response.getWriter().println("Welcome to the bank! <br>Please log in with \\login");
		}
	}


}
