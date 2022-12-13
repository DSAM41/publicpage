<%@page import="publicpage.ManageUser"%>
<%@page import="publicpage.ItemUser"%>
<jsp:useBean id="u" class="publicpage.ItemUser"></jsp:useBean>  
<jsp:setProperty property="*" name="u"/>  

<%  
String staffid = request.getParameter("staffid"); 
String user = request.getParameter("username");    
String pwd = request.getParameter("pass");
String email = request.getParameter("email");
String role = request.getParameter("role");
String[] checkboxNamesList= request.getParameterValues("hopo");
String hopo = "";
for (int i = 0; i < checkboxNamesList.length; i++) {
	hopo = hopo+checkboxNamesList[i];
	if(i < checkboxNamesList.length-1){
		hopo = hopo+":";
	}
}
String remark = request.getParameter("remark");

u.setStaffId(staffid);
u.setUsername(user);
u.setPassword(pwd);
u.setEmail(email);
u.setRole(role);
u.setAirportlist(hopo);
u.setRemark(remark);
int i=ManageUser.save(u);  
response.sendRedirect("management.jsp");
%>  