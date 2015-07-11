<%@page import="com.javaConnect.auth.model.User"%>
<%@page import="com.javaConnect.main.model.Post"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>PageDown-Bootstrap Demo Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link href="bootstrap-3.3.4-dist/styles.css" rel="stylesheet">
    
   

</head>
<body>
	<%
	/* String difference = (String)request.getAttribute("fromProfile");
	ArrayList<Post> posts;
	if( difference != null && difference.equals("true")){
		 posts = (ArrayList<Post>) request.getAttribute("userList");
	}else{
		 posts = (ArrayList<Post>) session.getAttribute("posts");
	}	 */
	ArrayList<User> userlist = (ArrayList<User>) request.getAttribute("userList");
	%>
	
  <ul class="posts" style="margin-top: 10em; ">
        <%for(User post: userlist){ %>
            <li class="post">
                <div class="post-thumbnail">
                	<% String username2 = post.getUsername();%>
                   
                </div>
                <div class="post-content">
                    <div class="post-date"><%=post.getContact() %></div>
                    <div class="post-author">
                        <a href="profile?username=<%=username2%>">
                           <%= username2%>
                        </a>
                    </div>
                    <%-- <%  String con  = post.getContact();%> --%>
					<div  class="post-body sample">
                   <h4>Academic grade: <%= post.getAvg() %>%</h4>
                    </div>
                    <div  class="post-body sample">
                   <h2>Skills: <%= post.getSk() %></h2>
                    </div>
                    <div  class="post-body sample">
                   <h2>Preffered working location: <%= post.getLoc() %></h2>
                   <h3>Academic grade: <%= post.getAvg() %></h3>
                    </div>
					                   
            </li>
        <%} //for ends %>
    </ul>
</body>
</html>
    