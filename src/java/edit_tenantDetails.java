
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


public class edit_tenantDetails extends HttpServlet {

   
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
         String bAadhaarNo = request.getParameter("AadhaarNo");
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
           
            sql = "Select * from tenant_details " + "where (Aadhaar  = '"+bAadhaarNo + "');";
            System.out.println(sql);
            Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
                System.out.println("Verified successfully : "+sql);
                row = 1;
                String bfname = rs.getString("fname");
                String bmname = rs.getString("mname");
                String blname = rs.getString("lname");
                String badharNo = rs.getString("adharNo");
                String bphno = rs.getString("phno");
                String bdob = rs.getString("dob");
                String bemail = rs.getString("email");
                String baddr = rs.getString("addr");
                String bdrno = rs.getString("drno");
                String bzipcode = rs.getString("zipcode");
                String bdistrict = rs.getString("district");
                String bstate = rs.getString("state");
                String bfamily_size = rs.getString("family_size");
                String brent = rs.getString("rent");
                String bhouse_type = rs.getString("house_type");
                
            System.out.println(bfname);
            System.out.println(bmname);
            System.out.println(blname);
            System.out.println(badharNo);
            System.out.println(bphno);
            System.out.println(bdob);
            System.out.println(bemail);
            System.out.println(baddr);
            System.out.println(bdrno);
            System.out.println(bdistrict);
            System.out.println(bzipcode);
            System.out.println(bstate);
            System.out.println(bfamily_size);
            System.out.println(brent);
            System.out.println(bhouse_type);
                                


                                
                
                RequestDispatcher rd = request.getRequestDispatcher("/edithouseDetails.html");  
                rd.forward(request, response); 
                  }
             if ( row == 0) {
                System.out.println("Enter valid Details : "+sql);
                PrintWriter html_out = response.getWriter();
                html_out.print("Sorry Aadhaar_No not Valid!");  
                html_out.print(" ");
                RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
                rd.include(request, response);
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
    


            