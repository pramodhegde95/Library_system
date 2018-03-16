<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
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
body{
 background: linear-gradient(to right,lightblue, white);
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4> Payment of Fine</h4>

<div class="form">
		<form action="PaymentOfFine">
			Enter the card ID <input type="text" name="fines">
		</form>
</div>

<%Iterator itr;%>		
<% 
	ArrayList rows=new ArrayList();
    if (request.getAttribute("data") != null){
	 rows=(ArrayList)request.getAttribute("data");
	 
	 
%>
<form action="PaidFine">
<table>
<tr>
<td width="168"><b>LOAN ID</b></td>
<td width="168"><b>DATE IN</b></td>
<td width="168"><b>FINE AMOUNT</b></td>
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
<%int i=Integer.valueOf((String)itr.next());
if(i==0){
%>

<td width="168"><input type="radio" name="checkbtn" value="<%=st%>"></td>
<%}
else{
%>
<td width="168">Paid</td>
<%}%>
<%}%>

	 </tr>
	 <tr>
	 <td>
	 <input type="Submit" value="Pay">
	 </td>
	 </form>
	 </tr>
<%}%>

</table> 
</body>
</html>