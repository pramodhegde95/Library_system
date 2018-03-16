<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
div.form {
	margin-top: 100px;
	text-align: center;
}
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}
tr:nth-child(even) {
    background-color: #dddddd;
}
body {
 background: linear-gradient(to right,lightblue, white);
 }
</style>
</head>
<body>

<div class="form">
		<form action="CheckIntoBorrower">
			Search Borrower <input type="text" name="borrower">
		</form>
</div>

	
<%Iterator itr;%>		
<% 
	ArrayList rows1=new ArrayList();
    if (request.getAttribute("data") != null){
	 rows1=(ArrayList)request.getAttribute("data");
	 
	 
%>

<form action="Check_intoborrower">
<table style="width:100%">
<tr>
<td width="168"><b>NAME</b></td>
<td width="168"><b>ISBN</b></td>
<td width="168"><b>CARD ID</b></td>
</tr>
<%
	 for (itr=rows1.iterator(); itr.hasNext();)
	 {		
 %> 
<tr>
<td width="168"><%=itr.next()%></td>
<%String st=(String)itr.next();
%>
<td width="168"><%=st%></td>
<td width="168"><%=itr.next()%></td>
<%String st1=(String)itr.next();%>
<td width="168"><input type="radio" name="checkbtn" value="<%=st%>"></td>
<%}%>
</tr>
<tr>
<td><input type="Submit" value="Check In"></td>
</tr>
</form>
</form>
<%}%>

</table> 
      
</body>
</html>