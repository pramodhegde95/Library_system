<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
    font-family: "Times New Roman", Times, serif;
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
h4 {
    text-align: left;
    font-family: "Times New Roman", Times, serif;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h4> Fines Table</h4>
<%Iterator itr;%>		
<% 
	ArrayList rows=new ArrayList();
    if (request.getAttribute("data") != null){
	 rows=(ArrayList)request.getAttribute("data");
	 
	 
%>
<table>
<tr>
<td width="168"><b>CARD ID</b></td>
<td width="168"><b>NAME</b></td>
<td width="168"><b>FINE</b></td>
</tr>
<%
	 for (itr=rows.iterator(); itr.hasNext();)
	 {		
 %> 
<tr>
<td width="168"><%=itr.next()%></td>
<td width="168"><%=itr.next()%></td>
<td width="168"><%=itr.next()%></td>
<%}%>
</tr>
<%}%>

</table> 
      
</body>
</html>