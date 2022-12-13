<%@page import="publicpage.ManageUser"%>
<%@page import="publicpage.ItemUser"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="publicpage.DatabaseConnect"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
	String SESSIONID=session.getId()+"";
	String USERNAME=session.getAttribute("username")+"";
	session.setMaxInactiveInterval(-1);//Never Expired
	Connection con = DatabaseConnect.getConnection();
	Statement st = con.createStatement();
	ResultSet rs1;
	String sql2="select * from session_members where session_id='"+session.getId()+"' and username='" + session.getAttribute("username") + "'";
	rs1 = st.executeQuery(sql2);
	boolean validsession=false;
	if(rs1.next()){
		validsession=true;
	}
	rs1.close();
	st.close();
	con.close();
	if(validsession){
	List<ItemUser> list=ManageUser.getAllRecords();
	String usernameList = "";
	for(ItemUser user:list){
		usernameList += user.getUsername()+"|";
	}
%>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<script src="jquery.2.2.0.min.js"></script>
<style><%@include file="/WEB-INF/css/loginStyle.css"%></style>
<script type="text/javascript">
        $(function () {
            $("#register").click(function () {
                var result = confirm("Do you want to save?");
                if (result == true) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
</script>
</head>
<body>
<% String id=request.getParameter("staffid");  
ItemUser u=ManageUser.getRecordById(id);  %>

	<div class="image_header">
	<img src="image/svp.png"></img>
	</div>
  <form class="modal-content animate" method="post" action="editUser.jsp" onsubmit="return validate(this)">
  <input type="hidden" name="staffid" value="<%=u.getStaffId()%>"/>  
    <div class="container">
      <label for="uname"><b>Username</b></label>
      <input type="text" name="username" value="<%=u.getUsername()%>" onkeypress="clearValidation()" required>
      <label for="psw"><b>Password</b></label>
      <input type="text" name="pass" value="<%=u.getPassword()%>"  required>
      <label for="email"><b>Email</b></label>
      <input type="text" name="email"  value="<%=u.getEmail()==null?"":u.getEmail()%>">
      <div>
	      <label for="hopo"><b>HOPO</b></label><br>
	      <input type="checkbox" id="BKK" name="hopo" value="BKK" required>
		  <label for="bkk"> BKK</label>
	      <input type="checkbox" id="DMK" name="hopo" value="DMK" required>
		  <label for="dmk"> DMK</label>
	      <input type="checkbox" id="HKT" name="hopo" value="HKT" required>
		  <label for="hkt"> HKT</label><br>
	      <input type="checkbox" id="HDY" name="hopo" value="HDY" required>
		  <label for="hdy"> HDY</label>
	      <input type="checkbox" id="CNX" name="hopo" value="CNX" required>
		  <label for="cnx"> CNX</label>
	      <input type="checkbox" id="CEI" name="hopo" value="CEI" required>
		  <label for="cei"> CEI</label><br>
	  </div>
      <label for="remark"><b>Remark</b></label>
      <input type="text" name="remark" value="<%=u.getRemark()==null?"":u.getRemark()%>">
      <button type="submit" id="register" >Save</button> <button type="button" onclick="window.location.replace('management.jsp');">Cancel</button>
    </div>
  </form>



<script>
// Get the modal
var modal = document.getElementById('id01');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

jQuery(function($) {
  var requiredCheckboxes = $(':checkbox[required]');
  requiredCheckboxes.on('change', function(e) {
    var checkboxGroup = requiredCheckboxes.filter('[name="' + $(this).attr('name') + '"]');
    var isChecked = checkboxGroup.is(':checked');
    checkboxGroup.prop('required', !isChecked);
  });
  requiredCheckboxes.trigger('change');
});

function validateUsername(){
	var isExists = false;
	var username = document.getElementsByName('username')[0];
	var usernameList = "<%=usernameList%>";
	var usernameSplit = usernameList.split("|");
	if("<%=u.getUsername()%>" != username.value){
		for(var i=0;i<usernameSplit.length;i++){
			if(usernameSplit[i] == username.value){
				isExists = true;
		 		i=usernameSplit.length;
			}
		}
		if (isExists) {
			username.setCustomValidity("Username is exists.");
		}else{
			username.setCustomValidity("");
		}
	}
}

function clearValidation(){
	var username = document.getElementsByName('username')[0];
	username.setCustomValidity("");
}

document.getElementById("register").onclick = validateUsername;

function setHopoCheckbox(){
	var isCheck = false;
	var hopo = "<%=u.getAirportlist()%>";
	var airportlist = hopo.split(":");
	for(var i=0; i<airportlist.length;i++){
		document.getElementById(airportlist[i]).checked = true;
	}
}
setHopoCheckbox();
</script>
</body>
<%
	}else{
		session.setAttribute("username", null);
		session.invalidate();
		response.sendRedirect("index.jsp?reason=sessioninvalid&SID="+SESSIONID+"&UNAME="+USERNAME);
	}

%>
</html>