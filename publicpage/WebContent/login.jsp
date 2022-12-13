<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="publicpage.DatabaseConnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String userid = request.getParameter("username");    
String pwd = request.getParameter("pass");

Connection con = DatabaseConnect.getConnection();
Statement st = con.createStatement();
ResultSet rs;
rs = st.executeQuery("select * from PUBLICPAGE_STAFF where username='" + userid + "' and password='" + pwd + "'");

if (rs.next()) {
	String username=rs.getString("username");
	String role=rs.getString("role");
	String airportlist=rs.getString("hopo");
    session.setAttribute("username", username);
    session.setAttribute("role", role);
    session.setAttribute("airportlist", airportlist);
    session.setMaxInactiveInterval(-1);//Never Expired
	Statement st2 = con.createStatement();
	Statement st3 = con.createStatement();
	Statement st4 = con.createStatement();
	int rs4;
	//String sqlUp="update session set id='"+session.getId()+"' where members_id='"+session.getAttribute("userid")+"'";
	//ResultSet rs1,rs2;

	String sqlDel="delete from session_members where username='"+username+"'";
	st3.execute(sqlDel);
// 	System.out.println(sqlDel);
	String sqlIn="insert into session_members (session_id,username) values ('" +session.getId() + "','" +username + "')";
	rs4 = st2.executeUpdate(sqlIn);
// 	System.out.println(sqlIn);
	
	st4.close();
	st3.close();
	st2.close();
	rs.close();
	st.close();
	con.close();
    if(role.equals("qa")){
    	response.sendRedirect("menuQatar.jsp");
    }
    else{
    	response.sendRedirect("menuAD.jsp");
    }
}else {
    out.println("Invalid password <a href='index.jsp'>try again</a>");
    st.close();
	con.close();
}
%>
</body>
</html>