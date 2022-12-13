<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="publicpage.DatabaseConnect"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Departure Menu</title>
</head>
<body>
<%    
	String SESSIONID=session.getId()+"";
	String USERNAME=session.getAttribute("username")+"";
	session.setMaxInactiveInterval(-1);//Never Expired
	Connection con = DatabaseConnect.getConnection();
	Statement st = con.createStatement();
	ResultSet rs1,rs2;
	String sql2="select * from session_members where session_id='"+session.getId()+"' and username='" + session.getAttribute("username") + "'";
	rs1 = st.executeQuery(sql2);
	if(rs1.next()){
		String[] airportlist = session.getAttribute("airportlist").toString().split(":"); 
		int airportSize = airportlist.length;
		
			%>
			<div class="wrapper">
			<div class="box-wrapper">
				<div class="header-menu">
			     <p>User : <%=session.getAttribute("username") %></p>
				 <a href="menuAD.jsp">Back</a>
				</div>
				<div class="navbar-menu">
				<p>DEPARTURE Page</p>
				<%for(int i = 0; i < airportSize; i++){%>
					<a class="link" href="departureFlight<%=airportlist[i] %>.jsp"><%=airportlist[i] %></a>
				<%}%>
<!-- 				<a class="link" href="departureFlightBKK.jsp">BKK</a> <a class="link" href="departureFlightDMK.jsp">DMK</a> -->
<!-- 				<a class="link" href="departureFlightHKT.jsp">HKT</a> <a class="link" href="departureFlightHDY.jsp">HDY</a> -->
<!-- 				<a class="link" href="departureFlightCNX.jsp">CNX</a> <a class="link" href="departureFlightCEI.jsp">CEI</a>	 -->
				</div>
			</div>
			</div>
			<%
	}
	else{
		session.setAttribute("username", null);
		session.invalidate();
		response.sendRedirect("index.jsp?reason=sessioninvalid&SID="+SESSIONID+"&UNAME="+USERNAME);
	}

			%>	
</body>
</html>