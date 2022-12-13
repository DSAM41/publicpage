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
<title>Qatar Menu</title>
<script language="JavaScript">
function Full_W_P(url) {
 params  = 'width='+screen.width;
 params += ', height='+screen.height;
 params += ', top=0, left=0'
 params += ', fullscreen=yes';


 newwin=window.open(url,'popup', params);
 if (window.focus) {newwin.focus()}
 return false;
}
</script>
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
		
			%>
			<div class="wrapper">
			<div class="box-wrapper">
				<div class="header-menu">
			     <p>User : <%=session.getAttribute("username") %></p>
				 <a href="logout.jsp">Log out</a>
				</div>
				<div class="navbar-menu">
				<a class="link" target="popup" onclick="javascript:Full_W_P('qatarDepart-Vertical.jsp');">Vertical Page</a>
				<a class="link" target="popup" onclick="javascript:Full_W_P('qatarDepart.jsp');">Horizontal Page</a>
	
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