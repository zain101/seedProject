<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="bootstrap-3.3.4-dist/brand.ico">

    <title>SignUp</title>
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    
  </head>

  <body>
    <div class="container">
    <%	
    	String status = (String)request.getAttribute("status");
    	if ( status!=null && status.equals("true"))
    	{
    %>
	    <div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	  			<span aria-hidden="true">&times;</span>
		</button>
	    <b>You have registered successfully</b></div>
    
    <%
    }
    %>
    <%	
    	String error = (String)request.getAttribute("error");
    	if ( status!=null && error.equals("true"))
    	{
    	
    %>
	    <div class="alert alert-danger alert-dismissible" role="alert">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  <strong>Warning!</strong> Username already exist choose a different username
</div>
    
    <%
    }
    %>
    <legend ><h3 style="float: center"><a href="index.jsp">HOME</a></h3> </legend>
    
    
	<form class="form-horizontal" action="SearchServlet" method="get" enctype="multipart/form-data">
  <fieldset>
  
    <div id="legend">
      <legend class=""> Search.</legend>
    </div>
     <div class="control-group">
      <!-- location -->
      <div class="controls">
      <label class="control-label"  for="skills">Skills.</label><br />
		<select id = "skills" name="skills" multiple="5" >
			<option > C</option>
			<option >C++</option>
			<option>JAVA</option>
			<option>.Net</option>
			<option> sql</option>
		</select>
        <p class="help-block">Where would you like to work.</p>
      </div>
    </div>
    
       
      <div class="control-group">
      <!-- location -->
      <div class="controls">
      <label class="control-label"  for="location">Preferred Location</label><br />
		<select id = "location" name="location" multiple="3" >
			<option>Mumbai</option>
			<option>Delhi</option>
			<option>Pune</option>
			<option>Bangalore</option>
		</select>
        <p class="help-block">Where would you like to work.</p>
      </div>
    
     <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="avg">Aggregate.</label>
      <div class="controls">
        <input type="text" id="avg" name="avg" placeholder="Aggregate Marks" class="input-xlarge">
        <p class="help-block">Percentage to short list</p>
      </div>
    </div>
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn  btn-primary">Register</button>
      </div>
    </div>
	</form>

    </div> <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
