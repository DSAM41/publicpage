<%@page import="publicpage.ManageUser"%>
<%@page import="publicpage.ItemUser"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="publicpage.DatabaseConnect"%>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
    // System.out.println();
	String SESSIONID=session.getId()+"";
	String USERNAME=session.getAttribute("username")+"";
	session.setMaxInactiveInterval(-1);//Never Expired
	Connection con = DatabaseConnect.getConnection();
	//System.out.println(con);
	Statement st = con.createStatement();
	//System.out.println(st);
	ResultSet rs;
	String sql="select * from session_members where session_id='"+SESSIONID+"' and username='" + USERNAME + "'";
	rs = st.executeQuery(sql);
	boolean validsession=false;
	if(rs.next()){
		validsession=true;
	}
	rs.close();
	st.close();
	con.close();
	if(validsession){    
	  	List<ItemUser> list=ManageUser.getAllRecords();
	    System.out.println(list);
%>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management User</title>
<style><%@include file="/WEB-INF/css/admin.css"%></style>
<link rel="stylesheet" href="font/css/font-awesome.min.css">

<script type="text/javascript">
	function deleteConfirm(staffId){
		var c = confirm("Do you want to delete?");
		if (c == true) {
			location.href = "deleteUser.jsp?staffid="+staffId;
		}
	}
	
	function editUser(staffId){
		location.href = "editUserForm.jsp?staffid="+staffId;
	}
</script>
</head>
<body> 
<section>
  <!--for demo wrap-->
  <h1>Admin Page</h1>
  <div style="float: right; position: relative; padding-top: 25px; padding-right: 10px;">
    <a class="exportButton" href="./ExportExcelUser">
    	<img src="image/icon_excel.png" alt="Export Excel" width="32" height="32"/>
    </a>&nbsp;
    <a class="exportButton" href="./ExportPDFUser">
    	<img src="image/icon_pdf.png" alt="Export PDF" width="32" height="32"/>
    </a>&nbsp;
    <a class="exportButton" href="./ExportCSVUser">
    	<img src="image/icon_csv.png" alt="Export CSV" width="32" height="32"/>
    </a>
  </div>
  <div class="menu_admin">
    <a href="addUserForm.jsp" title="Button fade lightblue" class="button btnFade btnLightBlue">Add Member</a>
    <a href="menuAD.jsp" title="Button fade lightblue" class="button btnFade btnLightBlue">Back to Menu</a>
    <div class="clear"></div>
  </div>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>Username</th>
          <th>Password</th>
          <th>Email</th>
          <th>Role</th>
          <th>Remark</th>
          <th>Management</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <%for(int i = 0; i < list.size(); i++){%>
        <tr>
          <td><%=list.get(i).getUsername()%></td>
          <td><%=list.get(i).getPassword()%></td>
          <td><%=list.get(i).getEmail()==null?"":list.get(i).getEmail()%></td>
          <td><%=list.get(i).getRoleName()%></td>
       	  <td><%=list.get(i).getRemark()==null?"":list.get(i).getRemark()%></td>
          <td><button class="btn" onclick="editUser('<%=list.get(i).getStaffId()%>')"><i class="fa fa-pencil-square-o"></i> Edit</button>
          &nbsp;&nbsp;&nbsp;
          <button class="btn delete" onclick="deleteConfirm('<%=list.get(i).getStaffId()%>')"><i class="fa fa-trash-o"></i> Delete</button></td>
        </tr>
             <%} %>
      </tbody>
    </table>
  </div>
</section>
</body>
<%
	}else{
		session.setAttribute("username", null);
		session.invalidate();
		response.sendRedirect("index.jsp?reason=sessioninvalid&SID="+SESSIONID+"&UNAME="+USERNAME);
	}

%>
</html>
    