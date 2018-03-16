<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
h4 {
    text-align: left;
    font-family: "Times New Roman", Times, serif;
}
body{
 background: linear-gradient(to right,lightblue, white);
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4 >Borrower Form</h4>
<form action="InsertBorrower">
<table>
<tr><td>Enter SSN(***-**-**** ) </td><td><input type="text" name="ssn"></td></tr>
<tr><td>Enter First Name</td><td><input type="text" name="fname"></td></tr>
<tr><td>Enter Last Name </td><td><input type="text" name="lname"></td></tr>
<tr><td>Enter Email </td><td><input type="text" name="email"></td></tr>
<tr><td>Enter Address </td><td><input type="text" name="address"></td></tr>
<tr><td>Enter City </td><td><input type="text" name="city"></td></tr>
<tr><td>Enter State </td><td><input type="text" name="state"></td></tr>
<tr><td>Enter Phone number </td><td><input type="text" name="phone"></td></tr>
<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>
</body>
</html>