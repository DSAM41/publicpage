<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Log In</title>
<style><%@include file="/WEB-INF/css/loginStyle.css"%></style>
<script type="text/javascript">
	//This is done to make the following JavaScript code compatible to XHTML. <![CDATA[
	function setCookie(cName, cValue, cPath, cExpires)
	{
		cvalue = encodeURIComponent(cValue);
		if (cExpires == ""){
			var cdate = new Date();
			cdate.setMonth(cdate.getMonth() + 9);
			cExpires = "";//cdate.toUTCString();
		}
		if (cPath != ""){
			cPath = ";Path=" + cPath;
		}
		document.cookie = cName + "=" + cValue +";expires=Fri, 31 Dec 9999 23:59:59 GMT" + cExpires + cPath;
	}
	
	function getCookie(cname) {
	  let name = cname + "=";
	  let ca = document.cookie.split(';');
	  for(let i = 0; i < ca.length; i++) {
	    let c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}
	
	function checkCookie() {
	  let name = getCookie("PublicPageName");
	  if (name != "") {
// 	    alert("Welcome again " + name);
	  } else {
		  var delayInMilliseconds = 1000; //1 second
		  setTimeout(function() {
			  var element = document.getElementById("cookieBox");
			  element.classList.add("fadeIn");
			  element.classList.add("outer");
			  element.style.display = "inline";
		  }, delayInMilliseconds);
// 		  alert("Set cookie");
	  }
	}
	
	function closePopup(){
		var element = document.getElementById("cookieBox");
		element.style.display = "none";
		setCookie("PublicPageName","Cookie","/","");
	}
	checkCookie();
// 	alert(document.cookie);
	//]]>
</script>
</head>
<body> 
	<div class="image_header">
	<img src="image/svp.png"></img>
	</div>
  <form class="modal-content animate" method="post" action="login.jsp">
  <% 
  String reason=request.getParameter("reason");
  if(reason==null){
	  reason="";
  }else{
	  reason="<color style='color:red;'>"+reason+"</color>";
  }
  %>
    <div class="container">
      <label for="uname"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" required>
      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="pass" required>
      <button type="submit">Login</button>
  		<label for="messages"><b><%=reason %></b></label>
  		<div id="cookieBox" style="display:none;">
			<div style="color: white; padding-top:10px; margin-top:10px; text-align: center;">
				เว็บไซต์ของเรามีการเก็บคุ้กกี้ซึ่งเก็บข้อมูลว่าคุณใช้งานเว็บไซต์ของเราอย่างไรและช่วยให้เราจดจำคุณได้ เราใช้ข้อมูลนี้เพื่อนำมาสู่การทำให้ประสบการณ์การใช้เว็บไซต์ดียิ่งขึ้น และสามารถคลิกที่ Ok เพื่อยอมรับคุ้กกี้ทั้งหมด
				<br/>
				<input type="button" id="btnAccept" value="Ok" onclick="closePopup()"/>
			</div>
		</div>
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
</script>
</body>
</html>
    