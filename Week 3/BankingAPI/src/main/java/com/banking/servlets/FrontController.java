package com.banking.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getRequestURI(); // "/login
		String[] parts = path.split("/"); // ["","login"]
		
		switch (parts[1].toLowerCase()) {
		case "login": {

			break;
		}
		case "logout": {
			
			break;
		}
		case "register": {
			
			break;
		}
		default: {
			//welcome to the bank
		}
		}
	}


}
