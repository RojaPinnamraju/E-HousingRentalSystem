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
<%
try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
statement=connection.createStatement();
String  sql = "Select * from house_details " + "where (Aadhaar  = '"+bAadhaarNo+"');";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<!DOCTYPE html>
<html>
    <head><meta name="viewport" content="width=device-width, initial-scale=1"><style>
 *{
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}
  input {
  width: 20%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #9775FA;
  border-radius: 4px;
  box-sizing: border-box;
  display: inline-block;
}
input[type=submit] {
  width: 10%;
  background-color: blue;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #9775FA;
  display: inline-block;
}
label{
    display: inline-block;
    clear: left;
    width: 110px;
    text-align: left;
}
        </style></head>
    <body>
        <div class="fleee"><center>
        <h1>UPDATE HOUSE DETAILS</h1><div class="A">
  <form method="post" action="update-process.jsp">
<input type="hidden" name="AadhaarNo" value="<%=resultSet.getString("Aadhaar") %>">
<label for="AadhaarNo"><b>AadhaarNo</b></label>
<input type="text" name="AadhaarNo" value="<%=resultSet.getString("Aadhaar") %>">
<br>
<label for="FirstName"><b>FirstName</b></label>
<input type="text" name="FirstName" value="<%=resultSet.getString("fname") %>">
<br>
<label for="MiddleName"><b>MiddleName</b></label>
<input type="text" name="MiddleName" value="<%=resultSet.getString("mname") %>">
<br>
    <label for="LastName"><b>LastName</b></label>
<input type="text" name="LastName" value="<%=resultSet.getString("lname") %>">
<br>
  <label for="PhoneNo1"><b>PhoneNo</b></label>
<input type="text" name="PhoneNo" value="<%=resultSet.getString("phnnum") %>">
<br>
 <label for="Email"><b>Email</b></label>
<input type="text" name="Email" value="<%=resultSet.getString("email") %>">
<br>
<label for="Dr_No"><b>Dr_No</b></label>
<input type="text" name="Dr_No" value="<%=resultSet.getString("Dr_No") %>">
<br></div><div class="B">
<label for="Street"><b>Address</b></label>
<input type="text" name="Address" value="<%=resultSet.getString("Address") %>">
<br>
<label for="District"><b>Street</b></label>
<input type="text" name="Street" value="<%=resultSet.getString("Street") %>">
<br>
   <label for="District"><b>District</b></label>
<input type="text" name="District" value="<%=resultSet.getString("District") %>">
<br>
<label for="City"><b>City</b></label>
<input type="text" name="City" value="<%=resultSet.getString("City") %>">
<br>
<label for="Zipcode"><b>Zipcode</b></label>
<input type="text" name="Zipcode" value="<%=resultSet.getString("Zipcode") %>">
<br>
 <label for="State"><b>State</b></label>
<input type="text" name="State" value="<%=resultSet.getString("State") %>">
<br>
<label for="Sqft"><b>Sqft</b></label>
<input type="text" name="Sqft" value="<%=resultSet.getString("Sqft") %>">
<br>
<label for="HouseType"><b>HouseType</b></label>
<input type="text" name="HouseType" value="<%=resultSet.getString("House_type") %>">
<br>
<label for="Rent"><b>ApproxRent</b></label>
<input type="text" name="ApproxRent" value="<%=resultSet.getString("Rent") %>">
<br></div>
<input type="submit" value="submit">
</form></center></div>
<%
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%></center>
</body>
</html>