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
            $("#back").click(function () {
            	location.href = "management.jsp";
            });
        });
</script>
</head>
<body>

	<div class="image_header">
	<img src="image/svp.png"></img>
	</div>
  <form id="addUser" class="modal-content animate" method="post" action="addUser.jsp">
    <div class="container">
<!--      <label for="uname"><b>Staff ID</b></label> -->
<!--       <input type="text" placeholder="Enter StaffID" name="staffid" required> -->
      <label for="uname"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" onkeypress="clearValidation()" required>
      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="pass" required>
      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Enter Email" name="email">
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
      <select name="role">
      <option value="admin">Admin</option>
      <option value="qa">Qatar</option>
      <option value="sf">Staff</option>
      </select>
      <label for="remark"><b>Remark</b></label>
      <input type="text" placeholder="Enter Remark" name="remark">
      <button type="submit" class="btnregister" id="register">Register</button>
      <button type="button" class="btnback" id="back">Back</button>
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

function clearValidation(){
	var username = document.getElementsByName('username')[0];
	username.setCustomValidity("");
}

document.getElementById("register").onclick = validateUsername;

// function validateHopo(){
// 	var isCheck = false;
// 	var els = document.getElementsByName('hopo');
// 	for (var i=0;i<els.length;i++){
// 	  if ( els[i].checked ) {
// 		  isCheck = true;
// 	  }
// 	}
// 	if(!isCheck){
// 		alert("Please select HOPO.");
// 	}else{
// 		document.getElementById("addUser").submit();
// 	}
// }

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