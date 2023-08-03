<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String bAadhaarNo = request.getParameter("AadhaarNo");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/ehrs";
String database = "ehrs";
String userid = "uehrs";
String password = "pehrs";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<html>
<body>
<center>
<h1>HOUSE DETAILS</h1>

<table style=\"font-size:20px; text-align:center;\" border=4 width=90% height=30%>
<tr>
<td>Aadhar</td>
<td>fname</td>
<td>mname</td>
<td>lname</td>
<td>phnnum</td>
<td>email</td>
<td>dr_no</td>
<td>address</td>
<td>street</td>
<td>district</td>
<td>city</td>
<td>zipcode</td>
<td>state</td>
<td>sqft</td>
<td>house_type</td>
<td>rent</td>
<td>update</td>
</tr>
<%
try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
statement=connection.createStatement();
String  sql = "Select * from house_details " + "where (Aadhaar  = '"+bAadhaarNo + "');";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr>
<td><%=resultSet.getString("Aadhaar") %></td>
<td><%=resultSet.getString("fname") %></td>
<td><%=resultSet.getString("mname") %></td>
<td><%=resultSet.getString("lname") %></td>
<td><%=resultSet.getString("phnnum") %></td>
<td><%=resultSet.getString("email") %></td>
<td><%=resultSet.getString("Dr_No") %></td>
<td><%=resultSet.getString("address") %></td>
<td><%=resultSet.getString("street") %></td>
<td><%=resultSet.getString("district") %></td>
<td><%=resultSet.getString("city") %></td>
<td><%=resultSet.getString("zipcode") %></td>
<td><%=resultSet.getString("state") %></td>
<td><%=resultSet.getString("sqft") %></td>
<td><%=resultSet.getString("house_type") %></td>
<td><%=resultSet.getString("rent") %></td>
<td><a href="update.html?bAadharNo=<%=resultSet.getString("Aadhaar")%>">update</a></td>
</tr>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</center>

</body>
</html>