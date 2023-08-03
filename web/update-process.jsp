<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%! String driverName = "com.mysql.jdbc.Driver";%>
<%!String url = "jdbc:mysql://localhost:3306/ehrs";%>
<%!String user = "uehrs";%>
<%!String psw = "pehrs";%>
<%
String bAadhaarNo = request.getParameter("AadhaarNo");
String bfirstname=request.getParameter("FirstName");
String bmiddlename =request.getParameter("MiddleName");
String blastname=request.getParameter("LastName");
String bphnnum=request.getParameter("PhoneNo");
String bemail = request.getParameter("Email");
String bDr_No=request.getParameter("Dr_No");
String bAddress=request.getParameter("Address");
String bStreet=request.getParameter("Street");
String bDistrict=request.getParameter("District");
String bCity = request.getParameter("City");
String bZipcode=request.getParameter("Zipcode");
String bState=request.getParameter("State");
String bSqft=request.getParameter("Sqft");
String bHouse_type=request.getParameter("HouseType");
String bRent=request.getParameter("ApproxRent");
if(bAadhaarNo != null)
{
Connection con = null;
PreparedStatement ps = null;
//int AadhaarNo = Integer.parseInt(bAadhaarNo);
try
{
Class.forName(driverName);
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
String sql="Update house_details set Aadhaar =?,fname=?,mname=?,lname=?,phnnum=?,email=?,Dr_No=?,Address=?,Street=?,District=?,City=?,Zipcode=?,State=?,Sqft=?,House_type=?,Rent=? where Aadhaar="+bAadhaarNo;
ps = con.prepareStatement(sql);
ps.setString(1,bAadhaarNo);
ps.setString(2, bfirstname);
ps.setString(3, bmiddlename);
ps.setString(4, blastname);
ps.setString(5, bphnnum);
ps.setString(6, bemail);
ps.setString(7, bDr_No);
ps.setString(8, bAddress);
ps.setString(9, bStreet);
ps.setString(10,bDistrict);
ps.setString(11, bCity);
ps.setString(12, bZipcode);
ps.setString(13, bState);
ps.setString(14, bSqft);
ps.setString(15, bHouse_type);
ps.setString(16, bRent);        

ps.setString(6, bemail);
int i = ps.executeUpdate();
if(i > 0)
{
out.print("<h1><center>Record Updated Successfully</center></h1>");
}
else
{
out.print("<h1>There is a problem in updating Record.</h1>");
} 
}
catch(SQLException sql)
{
request.setAttribute("error", sql);
out.println(sql);
}
}
%>