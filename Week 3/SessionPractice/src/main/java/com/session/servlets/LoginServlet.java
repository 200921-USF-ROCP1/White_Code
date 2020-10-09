package com.session.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.session.dao.FakeDAO;
import com.session.manager.SessionManager;
import com.session.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(request.getReader());
		SessionManager.createSession(request);
		
		String username = node.get("Username").asText();
		System.out.println(username);
		
		String password = node.get("Password").asText();
		System.out.println(password);
		
		FakeDAO dao = new FakeDAO();
		User user = dao.login(username, password);
		if (user != null) {
			SessionManager.addUser(user);
			response.getWriter().println("Login successful");
		} else {
			//response.sendError(400);
			response.getWriter().println("Invalid username and password combination.");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionManager.createSession(request);
		User user = SessionManager.getUser();
		
		if (user != null) {
			response.getWriter().printf("%s is logged in!",user.getUsername());
		} else response.getWriter().println("No one is logged in.");
	}

}
