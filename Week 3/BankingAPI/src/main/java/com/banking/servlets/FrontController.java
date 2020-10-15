package com.banking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.interfaces.AccountService;
import com.banking.interfaces.UserService;
import com.banking.models.User;
import com.banking.services.AccountServiceImpl;
import com.banking.services.UserServiceImpl;
import com.banking.utils.Authentication;
import com.banking.utils.JsonInterp;
import com.banking.utils.SessionManager;
import com.fasterxml.jackson.databind.JsonNode;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JsonInterp<User> interp = new JsonInterp<User>(User.class);
	private static UserService uServ = new UserServiceImpl();
	private static AccountService aServ = new AccountServiceImpl();
	private static SessionManager sm = new SessionManager();
	
	private JsonNode node;
	private static User user;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in

		String path = request.getRequestURI(); // "/BankingAPI/login
		String[] parts = path.split("/"); // ["","BankingAPI","login"]
		
		if (parts.length >= 3) {
			switch (parts[2].toLowerCase()) {
			case "login": {
				if (sm.getUser() == null) { //dont allow someone to login if someone is already logged in
					node = interp.getNode(request);
					String username = node.get("username").asText();
					String password = node.get("password").asText();
						user = uServ.login(username, password);
						
					if (user != null) { //login successful
						sm.addUser(user);
						response.setStatus(202); //accepted
						System.out.printf("%s %s has logged in.\n\n",user.getFirstName(),user.getLastName());
						response.getWriter().println(interp.marshal(user));
					} else response.sendError(401, "Invalid username and password combination.");
					
				} else response.sendError(405, "Please log out before logging in a new user."); //method not allowed
				break;
			}
			case "logout": {
				if (sm.getUser() == null) //no user logged in
					response.sendError(403, "Not logged in.");
				else {
					sm.closeSession();
					response.getWriter().println("You have been logged out.");
				}
				break;
			}
			case "register": {
				if (sm.getUser() == null) { //check if logged in 
					response.sendError(403, "Not logged in.");
					break;
				}
				if (Authentication.canAccess("Admin")) { //must be admin
					user = interp.unmarshal(request); //get user object from body
					user.setRole(user.roleId);
					uServ.register(user); //create user in db
					response.getWriter().println(interp.marshal(user));
				} else response.sendError(401, "Only an admin can register a new user.");
				break;
			}
			case "time": { // add interest to accounts based on time
				if (sm.getUser() == null) { //check if logged in 
					response.sendError(403, "Not logged in.");
					break;
				}
				if (Authentication.canAccess("Admin")) { //must be admin
					node = interp.getNode(request);
					int months = node.get("months").asInt();
					aServ.accrueInterest(months); //add interest to accounts
					response.setStatus(202); //accepted
					response.getWriter().println(String.format("%d months have passed...", months));
					
				} else response.sendError(401, "Only an admin can accelerate time itself.");
				break;
			}
			}
			
		} else { //no argument after host '/BankingAPI'
			response.getWriter().println("Welcome to the bank! <br>Please log in with \\login");
		}
	}
}
