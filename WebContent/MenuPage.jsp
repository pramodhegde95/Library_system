<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div.form {
	margin-top: 100px;
	text-align: center;
	color:black;
}

p {
	text-align: center;
	border-style: outset;
	border-width: 1px;
	border-color:white;
	text-size: 30px;
	color: black;
}
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
    background: white;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
body {
 background: linear-gradient(to right,lightblue, white);
 }

.dropbtn {
    background-color: lightgrey;
    color: black;
    padding: 8px;
    font-size: 13px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: lightbrown;
}
.button {
    background-color: lightbrown;
    border: none;
    outline:none;
    
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="head">
		<p>Library System</p>
	</div>


<div class="dropdown">
  <button class="dropbtn">Menu</button>
  <div class="dropdown-content">
  <a href="Insertborrower.jsp" class="button"><input type="button" value="Insert Borrower" /> </a>
  <a href="CheckInto.jsp" class="button"><input type="button" value="Check in" /></a>
  <a class="button"><form action="CalculateFines">
			<input type="submit" value="Update Fines">
		</form></a>
  <a href="FinePayment.jsp"class="button"><input type="button" value="Fine payment" /> </a>
  <a class="button"><form action="FinesOfUser">
			<input type="submit" value="Display fines">
		</form></a>
		</div>
</div>
  
</ul>
	
<div class="form">
		<form action="ControllerServlet">
			Search <input type="text" name="search">
		</form>
</div>
		
<%Iterator itr;%>		
<% 
	ArrayList rows=new ArrayList();
    if (request.getAttribute("data") != null){
	 rows=(ArrayList)request.getAttribute("data");
	 
	 
%>
<form action="CheckIn">
<table style="width:100%">
<tr>
<td width="168"><b>ISBN</b></td>
<td width="168"><b>TITLE</b></td>
<td width="168"><b>NAME</b></td>
<td width="168"><b>AVAILABLE</b></td>
</tr>
<%
	 for (itr=rows.iterator(); itr.hasNext();)
	 {		
 %> 
<tr>
<%String st=(String)itr.next(); %>
<td width="168"><%=st%></td>
<td width="168"><%=itr.next()%></td>
<td width="168"><%=itr.next()%></td>
<%
int i=Integer.valueOf((String)itr.next());
if(i==1){
%>

<td width="168"><input type="radio" name="checkbtn" value="<%=st%>"></td>
<%}
else{
%>
<td width="168">Checked Out</td>
<%}%>

<%}%>
	 </tr>
	 <tr>
	 <td>
	 Enter Card ID <input type="text" name="cardid"></br>
	 <input type="Submit" value="Check Out">
	 </form>
	 </tr>
<%}%>

</table> 
      
</body>
</html>