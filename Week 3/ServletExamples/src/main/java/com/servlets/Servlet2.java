package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet {
	
	private static final long serialVersionUID = -3227855691757751056L;

	public void doGet (HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter pw = response.getWriter(); //writing directly to response body
			pw.println("Second serlet");
			response.setStatus(200); //send code
			//response.sendError(401); //send error
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
