<%@page import="java.util.Calendar"%>
<%@page import="publicpage.ItemArrival"%>
<%@page import="publicpage.DataArrival"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="publicpage.DataFlight"%>
<%@page import="publicpage.ItemDeparture"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="publicpage.DatabaseConnect"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Arrival Flight</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery.2.2.0.min.js"></script>
<style><%@include file="/WEB-INF/css/style.css"%></style>

	<%    
	String SESSIONID=session.getId()+"";
	String USERNAME=session.getAttribute("username")+"";
	session.setMaxInactiveInterval(-1);//Never Expired
	 Connection con = DatabaseConnect.getConnection();
	int rs;
	//String sqlUp="update session set id='"+session.getId()+"' where members_id='"+session.getAttribute("userid")+"'";
	Statement st = con.createStatement();
	ResultSet rs1;
	String sql2="select * from session_members where session_id='"+SESSIONID+"' and username='" + USERNAME + "'";
	rs1 = st.executeQuery(sql2);
	boolean validsession=false;
	if(rs1.next()){
		validsession=true;
	}
	rs1.close();
	st.close();
	con.close();
	if(validsession){
		List<ItemArrival> list=DataArrival.getDataArrivalDMK();  
		  request.setAttribute("list",list);

		  Date todayDate=new Date();
		  SimpleDateFormat dateOn = new SimpleDateFormat("dd/MM/yyyy");
		  String showDate = dateOn.format(todayDate);
		  String newDateUp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
		     %>
<script type="text/javascript">
 $(document).ready(function(){
	 pageScroll();
// 	function scrollDown(el) {
<%--     	el.animate({scrollTop: el[0].scrollHeight}, <% out.print(list.size()*1000);%>,reload); --%>
// 	};

// function reload() {
// 	window.location.reload(true); 
// };

// scrollDown($('#contain'));

}); 

 var totalHeight = 0;
 function pageScroll() {
	 document.getElementById("contain").scrollTop += 1; // horizontal and vertical scroll increments
	  scrolldelay = setTimeout('pageScroll()', 50); // scrolls every 100 milliseconds
	  if (document.getElementById("contain").scrollTop == totalHeight) {
		  clearTimeout(scrolldelay);
		    if(totalHeight == 0){
			    scrolldelay = setTimeout('PageUp()', 10000);
		    }else{
			    scrolldelay = setTimeout('PageUp()', 2000);
		    }
			totalHeight = 0;
	  }else{
		totalHeight = document.getElementById("contain").scrollTop;
	  }

	 }

	 function PageUp() {
		 window.location.reload(true);
	 }

</script>
</head>
<body style="margin:0px;overflow:hidden;">
	     <table id="flights"><tr><th class="header_th"><img src="image/Arrivals.png"/>
    			     <div style="position: absolute;top: -3px;right: 35px;"><font size="10" color="#fff">DMK</font></div></th></tr></table>
		  <TABLE id="flights">
			    <tr>
			      <th width="10%" style='background-color:#003300 !important;'><font size="6">Time</font></th>
			      <th width="10%" style='background-color:#003300 !important;'><font size="6">Airlines</font></th>
			      <th width="30%" style='background-color:#003300 !important; text-align:left;'><font size="6">From</font></th>
			      <th width="10%" style='background-color:#003300 !important;'><font size="6">Flight</font></th>
			      <th width="10%" style='background-color:#003300 !important;'><font size="6">Arrives</font></th>
			      <th width="10%" style='background-color:#003300 !important;'><font size="6">Belt</font></th>
			      <th width="30%" style='background-color:#003300 !important;'><font size="6">Remark</font></th>
			    </tr>
			    </TABLE>
			     <div id="contain" style="padding-bottom: 50px;">
				 <div id="contain-one">
				      <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				<%for(int i = 0; i < list.size(); i++) 
				
				{%>		
				     <tr <% if(i%2==0) {out.print("class='arrivalrow2'");}else {out.print("class='arrivalrow1'");} %>>
				      <td width="10%" align="center"><font size="6" color="#ffffff"><%=list.get(i).getTimeArrival()%></font></td>
				      <td width="10%"><img style="border-radius: 5px;" src=<%=list.get(i).getAirline()%>></td>
				      <td width="30%"><font size="6" color="ffffff"><%=list.get(i).getFlightFrom()%></font></td>
				      <td width="10%" align="center"><font size="6" color="ffffff"><%=list.get(i).getFlight()%></font></td>
				      <td width="10%" align="center"><font size="6" color="ffffff"><%=list.get(i).getEstimate()%></font></td>
				      <td width="10%" align="center"><font size="6"><span class="badge badge-warning"><%=list.get(i).getBelt()%></span></font></td>
				      <td width="20%" align="center"><font size="6" color="ffffff"><%=list.get(i).getStatus()%></font></td>
				    </tr>
				    <% if(list.get(i).getJFNO()!=null && list.get(i).getJFNO().trim().length()>0){ %>
				    <tr <% if(i%2==0) {out.print("class='arrivalrow2'");}else {out.print("class='arrivalrow1'");} %>>
				    	<td width="10%" align="center"></td>
				    	<td width="90%" colspan="6" valign="middle"><font size="5"><%=list.get(i).getJFNO()%></font></td>
				    </tr> 
				    <%} %>
		       	<%}%> 
		       	</tbody>
				 </table>

					   </div>
					   </div>					
		 <div id="bottom-menu">
	        <p>Page Last Update On :<%=newDateUp+" ( Total : "+list.size() +" )"%> </p>
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