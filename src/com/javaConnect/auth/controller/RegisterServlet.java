package com.javaConnect.auth.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.User;

@MultipartConfig(maxFileSize = 16177215)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = (String) request.getAttribute("validUsername");

	/*	if(status.equals("success")){
				RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
				dispatch.forward(request, response);
		}
		else{
			RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
			dispatch.forward(request, response);
		}*/
		RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
		dispatch.forward(request, response);
	}
}
