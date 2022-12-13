<%@page import="publicpage.DataQatarVertical"%>
<%@page import="java.util.Calendar"%>
<%@page import="publicpage.DataQatar"%>
<%@page import="publicpage.ItemQatar"%>
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
<title>Departure Flight</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="jquery.2.2.0.min.js"></script>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<script type="text/javascript">
setTimeout(function() {
	window.location.reload(true);
	}, 30000);

</script>
</head>
<body style="margin:0px;overflow:hidden;">
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
		List<ItemQatar> list=DataQatarVertical.getDataDeparture();  
		  request.setAttribute("list",list);

		  Date todayDate=new Date();
		  SimpleDateFormat dateOn = new SimpleDateFormat("dd/MM/yyyy");
		  String departDate = dateOn.format(todayDate);
		  String newDateUp = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
	     %>
	     <table id="flights"><tr><th class="header_th" style='background-color: #681A41 !important;'><font size="6">Departure Flights on <%=departDate%></font></th></tr></table>
		  <TABLE id="flights" style="table-layout:fixed">
			    <tr>
			      <th width="13%" class="header-qatar"><font size="5">Time</font></th>
			      <th width="16%" class="header-qatar"><font size="5">Airline</font></th>
			      <th width="20%" class="header-qatar"><font size="5">To</font></th>
			      <th width="10%" class="header-qatar"><font size="5">Flight</font></th>
			      <th width="13%" class="header-qatar"><font size="5">Est. Time</font></th>
			      <th width="5%" class="header-qatar"><font size="5">Gate</font></th>
			      <th width="10%" class="header-qatar"><font size="5">Status</font></th>
			    </tr>
<!-- 			    </TABLE> -->
<!-- 			     <div id="contain" > -->
<!-- 				 <div id="contain-one"> -->
<!-- 				      <table cellpadding="0" cellspacing="0" border="0"> -->
				      <tbody>
				<%for(int i = 0; i < list.size(); i++) 
				
				{%>		
					
				     <tr <% if(i%2==0) {out.print("class='qatarrow4'");} else {out.print("class='qatarrow3'");} %>>
				      <td align="center"><font size="4" color="white"><%=list.get(i).getTimeDepart()%></font></td>
				      <td><img style="border-radius: 5px;" src="image/QR_L.gif"></img></td>
				      <td align="left" style="padding-left: 15px;"><font size="5" color="white"><%=list.get(i).getFlightTo()%></font></td>
				      <td><font size="5" color="white"><%=list.get(i).getFlight()%></font></td>
				      <td><font size="4" color="white"><%=list.get(i).getEstimate()%></font></td>
				      <td><font size="6" color="white"><span class="badge badge-warning"><%=list.get(i).getGate()%></span></font></td>
				      <td><font size="5" color="white"><%=list.get(i).getStatus()%></font></td>
				    </tr>	 
		       	<%}%> 
		       	</tbody>
				 </table>
<!-- 					   </div> -->
<!-- 					   </div>					 -->
		 <div id="bottom-menu"  style='background-color: #681A41 !important;'>
	      <p>Page Last Update On : <%=newDateUp %> ( Total : 20 )</p>
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