<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style><%@include file="/WEB-INF/css/css_log_test.css"%></style>
<script>
    $(document).ready(function(){
       $('#login').click(function()
       {   
          var user=$('#username').val();
          var pwd=$('#password').val();
          $.ajax({
               type: "GET",
               url:"Log_test",
               data:{"username":user,"password":pwd},
               success: function (data) {
                  if(data=='True'){
                    $(location).attr('href','table-test.jsp');
                  }else{
                      $('#errorMessage').css("display","block");
                  }
               }
             });                                
           });
         });
   </script>
</head>
<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first">
				<img src="https://pngfreely.com/wp-content/uploads/2021/09/%E0%B8%84%E0%B8%99-Png-Avatar-Icon-Profile-Icon-Member-Login-%E0%B9%84%E0%B8%AD%E0%B8%84%E0%B8%AD%E0%B8%99%E0%B9%82%E0%B8%9B%E0%B8%A3%E0%B9%84%E0%B8%9F%E0%B8%A5%E0%B9%8C-1024x630.jpg" id="icon"
					alt="User Icon" />
			</div>

			<!-- Login Form -->
			<form method="get">
				<input type="text" id="username" class="fadeIn second" name="username"
					placeholder="login"> 
				<input type="password" id="password"
					class="fadeIn third" name="password" placeholder="password"> 
				<input
					type="button" class="fadeIn fourth" value="Log In" id="login">
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover" href="#">Forgot Password?</a>
			</div>
			
			<div id="errorMessage" style="display: none;">
				<label style="color: red;"><b>Invalid password</b></label>
			</div>

		</div>
	</div>
</body>
</html>