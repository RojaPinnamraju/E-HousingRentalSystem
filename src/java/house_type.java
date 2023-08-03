
import java.io.IOException;
import java.io.PrintWriter;
/*import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet    */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.*;  
import java.io.PrintWriter;


public class house_type extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Validation(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html");  
        //PrintWriter out = response.getWriter();  
        //out.println(out);
        PrintWriter out = response.getWriter();
         out.println("bfname");

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     
      

    public void Validation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         String bZipcode = request.getParameter("Zipcode");
         String bHouseType = request.getParameter("HouseType");
         PrintWriter writer = response.getWriter();
         writer.println("<html-code>");
        // String bAadhaarNo = response.getParameter("AadhaarNo");

        
        
         

      //String fpass  = request.getParameter("pass");   
        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
     
            sql = "Select fname,mname,lname,street,city,phnnum,email from house_details " + "where (zipcode  = '"+bZipcode+"' and house_type  = '"+bHouseType+"');";
           // System.out.println(sql);
            Statement st = con.createStatement(); 
           /*int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            
            System.out.println("LIST SHOWING HOUSES AVAILABLE");
            //writer.println("<table border=1 width=50% height=50%>");  
            //writer.println("<tr><th>First_Name</th><th>Middle_Name</th><th>Last_Name</th><th>Street</th><th>City</th><th>Phnnum</th><th>Email</th><tr>");
            writer.println("<center><h1 style=\"font-size:40px\">LIST OF <B>"+bHouseType+"</B> HOUSES AVAILABLE</h1>"); 
            writer.println("<table style=\"font-size:20px; text-align:center;\" border=4 width=90% height=30%>"); 
            writer.println("<tr style=\"color:red; font-size:30px\"><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Street</th><th>City</th><th>Phnnum</th><th>Email</th><tr><style='font-size:60px;'>");
            int row = 0;
            while(rs.next()) {
               // System.out.println("Verified successfully : "+sql);
               
                row = 1;
               // String bAadhaar = rs.getString("Aadhaar");
                String bfname = rs.getString("fname");
                String bmname = rs.getString("mname");
                String blname = rs.getString("lname");
                String bstreet = rs.getString("street");
                String bcity = rs.getString("city");
                String bphnnum = rs.getString("phnnum");
                String bemail = rs.getString("email");
                writer.println("<tr><td>" + bfname + "</td><td>" + bmname + "</td><td>" + blname+ "</td><td>"+ bstreet+ "</td><td>"+ bcity+ "</td><td>"+ bphnnum+ "</td><td>"+ bemail+ "</td></tr>");   
             /*   String bdr_no = rs.getString("dr_no");
                String baddress = rs.getString("address");
                String bdistrict = rs.getString("district");
                String bzipcode = rs.getString("zipcode");
                String bstate = rs.getString("state");
                String bsqft = rs.getString("sqft");
                String bhouse_type = rs.getString("house_type");
                String brent = rs.getString("rent");
              */  
            //System.out.println(bAadhaar);
           // System.out.println(bfname+" "+bmname+" "+blname+" "+bstreet+" "+bcity+""+bphnnum+" "+bemail);
            /*
            System.out.println(bmname);
            System.out.println(blname);
            System.out.println(bphnnum);
            System.out.println(bemail);
            System.out.println(bdr_no);
            System.out.println(baddress);
            System.out.println(bstreet);
            System.out.println(bdistrict);
            System.out.println(bcity);
            System.out.println(bzipcode);
            System.out.println(bstate);
            System.out.println(bsqft);
            System.out.println(bhouse_type);
            System.out.println(brent);
            */
                                


                
                  }
            writer.println("</table>");  
           // writer.println("</html></body>"); 
            writer.println("</table>");
            writer.println("<script> \n" +
"    function welcome() { \n" +
"    window.open(\"house_type.html\");\n" +
"    } \n" +
"    </script>");
            writer.println("<div><p>&nbsp;</p></div>");
            writer.println("<form><button id=\"button\" type=\"button\" onclick=\"welcome()\"style=\"color:red; font-size:50px; border-radius:10px;\" formaction=\"report.html\">GO BACK</form></center>"); 
            
            writer.println("</html></body>"); 
             con.close();
            
             if ( row == 0) {
                System.out.println("Enter valid Details : "+sql);
                PrintWriter out = response.getWriter(); 
                out.println("<script type=\"text/javascript\">"); 
                out.println("alert('Sorry No Houses Found');"); 
                out.println("location='house_type.html';");   
                out.println("</script>"); 
                /*PrintWriter html_out = response.getWriter();
                html_out.print("Sorry No Houses Found !");  
                html_out.print(" ");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
                rd.include(request, response); */ 
             }
                    
         /*   if (m == 1) 
                System.out.println("Login successfully : "+sql); 
            else
                System.out.println("Login failed");    */
            con.close();
            }catch(Exception e)
                {System.out.println(e);}
    }        
   }
    


            