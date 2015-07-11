package com.javaConnect.filter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.javaConnect.auth.model.User;

/**
 * Servlet Filter implementation class RegisterFilter
 */
public class RegisterFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside Register Filter");
		User user = new User();
		Connection conn;
		ServletContext context = request.getServletContext();
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		user.setContact(Long.parseLong(request.getParameter("contact")));
		user.setAvg(Float.parseFloat(request.getParameter("avg")));
		user.setSsc(Float.parseFloat(request.getParameter("ssc")));
		user.setHsc(Float.parseFloat(request.getParameter("hsc")));
		user.setDeg(Float.parseFloat(request.getParameter("deg")));
		user.setSkills(request.getParameterValues("skills"));
		user.setLocation(request.getParameterValues("location"));
		String sk[] = user.getSkills();/*request.getParameterValues("skills");*/
		System.out.println(sk);
		for (int j =0; j< sk.length; j++)
		System.out.println(sk[j]);
		/*user.setPassword(request.getParameter("password"));
		user.setAbout(request.getParameter("about_you"));
		user.setLocation(request.getParameter("location"));
		InputStream inputstream = null;
		HttpServletRequest req  = (HttpServletRequest) request ;
		Part filePart =  req.getPart("pic");
		if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputstream = filePart.getInputStream();
    
        }
		if(inputstream != null)
			user.setProfilePic(inputstream);*/
		
		conn = (Connection)context.getAttribute("conn");
		
		 if(User.validateEmail(user.getEmail(), conn)){ 
			 if(User.insertUser(user, conn) &&  User.insertSkills(user, conn) && User.insertLocations(user, conn)){
				 request.setAttribute("status", "true");
				 request.setAttribute("error", "false");
			 }
		 }
		 else{
			 	request.setAttribute("validUsername", "failure");
				request.setAttribute("error", "true");
				request.setAttribute("status", "false");
		 }
	
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
