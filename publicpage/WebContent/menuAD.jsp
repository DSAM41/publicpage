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
<title>Menu</title>

<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<%    
	String SESSIONID=session.getId()+"";
	String USERNAME=session.getAttribute("username")+"";
	session.setMaxInactiveInterval(-1);//Never Expired
	Connection con = DatabaseConnect.getConnection();
	int rs;
	//String sqlUp="update session set id='"+session.getId()+"' where members_id='"+session.getAttribute("userid")+"'";
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
				 <a href="logout.jsp">Log out</a>
				</div>
				<div class="navbar-menu">
				<% 	if(session.getAttribute("role").equals("admin")) {%>
				<a class="link" href="management.jsp">Management</a>
				<a class="link" href="menuArrHOPO.jsp">Arrival Flight</a>
				<a class="link" href="menuDepHOPO.jsp">Departure Flight</a>
				<a class="link" href="menuQatar.jsp">QATAR Departure Flight</a>
				<%} else if ((session.getAttribute("role").equals("qa"))){%>
				<a class="link" href="menuQatar.jsp">QATAR Departure Flight</a>
				<%}  else if ((session.getAttribute("role").equals("sf"))){
						if(airportSize > 1){
				%>	
				<a class="link" href="menuArrHOPO.jsp">Arrival Flight</a>
				<a class="link" href="menuDepHOPO.jsp">Departure Flight</a>						
				<%		
						}else{
				%>	
				<a class="link" href="arrivalFlights<%=airportlist[0] %>.jsp">Arrival Flight</a>
				<a class="link" href="departureFlight<%=airportlist[0] %>.jsp">Departure Flight</a>						
				<%			
						}
				%>
				<%} %>
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