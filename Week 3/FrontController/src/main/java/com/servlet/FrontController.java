package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A front controller is a singular servlet that acts as a router for all requests.
 * Technically, you can only have this one.
 * can also route requests to different servlets
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI(); // "/FrontController/MyServlet/1
		String[] parts = path.split("/"); // ["","FrontController","MySerlet","1"]
		
		switch (parts[2].toLowerCase()) {
		case "car": {
			//can call car service here and pass request and response
			//can also get data from request and pass them to the DAO here
			// input = parts[3]
			// send back response
			break;
		}
		case "resident": {
			
			break;
		}
		case "pet": {
			
			break;
		}
		case "apartment": {
			
			break;
		}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
