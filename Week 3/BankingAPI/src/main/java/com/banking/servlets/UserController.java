package com.banking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.models.User;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getRequestURI(); // "/user/1
		String[] parts = path.split("/"); // ["","user","1"]
		
		if (parts.length >= 3) {
			try {
				int userId = Integer.parseInt(parts[2]);
				//lookup user by id
				
				
			} catch (NumberFormatException e){
				//invalid input
				
				return;
			}
		} else {
			//lookup all users
		}

	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update user
	}

}
