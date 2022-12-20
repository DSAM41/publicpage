<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="publicpage.User_test"%>
<%@page import="publicpage.UserService"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/css/css_table_test.css"%></style>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%
List<User_test> list=UserService.getUsers();
//request.setAttribute("list",list);
%>


<script>
    $(document).ready(function(){
       $('#update').click(function()
       {   
    	   var update_id=$('#update_id').val();
    	   var fname=$('#fname').val();
           var lname=$('#lname').val();
           var mail=$('#mail').val();
           console.log(update_id); 
           $.ajax({
               type: "GET",
               url:"Edit_test",
               data:{"update_id":update_id,"fname":fname,"lname":lname,"mail":mail},
               success: function (data) {
            	   console.log(data);
            	   //alert(data);
               }
             });                              
           });
         });
   </script>
<script>
        $(document).ready(function () {

            $('.editbtn').on('click', function () {

                $('#editmodal').modal('show');

                $tr = $(this).closest('tr');
                console.log($tr.children("td"));

                var data = $tr.children("td").map(function () {
                    return $(this).text();
                }).get();

                
                $('#update_id').val(data[0]);
                $('#fname').val(data[1]);
                $('#lname').val(data[2]);
                $('#mail').val(data[3]);
            });
        });
    </script>

</head>
<body>
<!-- EDIT POP UP FORM (Bootstrap MODAL) -->
    <div class="modal fade" id="editmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> Edit Data </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <form method="GET">

                    <div class="modal-body">

                        <input type="hidden" name="update_id" id="update_id">

                        <div class="form-group">
                            <label> First Name </label>
                            <input type="text" name="fname" id="fname" class="form-control"
                                placeholder="Enter First Name">
                        </div>

                        <div class="form-group">
                            <label> Last Name </label>
                            <input type="text" name="lname" id="lname" class="form-control"
                                placeholder="Enter Last Name">
                        </div>

                        <div class="form-group">
                            <label> Mail </label>
                            <input type="text" name="mail" id="mail" class="form-control"
                                placeholder="Enter Mail">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" id="update" name="updatedata" class="btn btn-primary">Update Data</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
    
    <table id="customers">
	<thead>
		<tr>
			<th>id</th>
			<th>first name</th>
			<th>last name</th>
			<th>mail</th>
			<th>edit</th>
		</tr>
		</thead>
		<tbody id="myBody">
		<%for(int i = 0; i < list.size(); i++){%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getFirstName()%></td>
			<td><%=list.get(i).getLastName()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><button type="button" class="btn btn-success editbtn"> EDIT </button></td>
		</tr>
		<%}%> 
		</tbody>
	</table>



</body>
</html>