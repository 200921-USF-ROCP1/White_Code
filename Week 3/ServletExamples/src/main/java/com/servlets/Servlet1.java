package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 6777397147243345778L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println("Hello World!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
