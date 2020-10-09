package com.banking.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
			response.getWriter().println(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request,response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		doGet(request,response);
	}
}
