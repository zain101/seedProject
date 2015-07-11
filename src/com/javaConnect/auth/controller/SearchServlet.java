package com.javaConnect.auth.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.User;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		User user = new User();
		ArrayList<User> userList; 
		ServletContext context = request.getServletContext();
		conn = (Connection) context.getAttribute("conn");
		
		user.setSkills(request.getParameterValues("skills"));
		user.setLocation(request.getParameterValues("location"));
		user.setDeg(Float.parseFloat(request.getParameter("avg")));

		userList = User.searchUser(user, conn);
		if (userList != null)
		for(User u : userList){
			System.out.println(u.getUsername());
			System.out.println(u.getEmail());
			System.out.println(u.getContact());
			System.out.println(u.getSk());
		}
		request.setAttribute("userList", userList);
		RequestDispatcher dispatch  = request.getRequestDispatcher("result.jsp");
		dispatch.forward(request, response);
	}

}
