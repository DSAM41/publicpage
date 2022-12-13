<%@page import="publicpage.ManageUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="u" class="publicpage.ItemUser"></jsp:useBean>  
<jsp:setProperty property="*" name="u"/>  
<%  
String staffid = request.getParameter("staffid"); 
u.setStaffId(staffid);
ManageUser.delete(u);  
response.sendRedirect("management.jsp");  
%>  