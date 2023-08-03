
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


public class edithouseDetails extends HttpServlet {

   
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
           
            sql = "Select * from house_details " + "where (Aadhaar  = '"+bAadhaarNo + "');";
            System.out.println(sql);
            Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
                System.out.println("Verified successfully : "+sql);
                row = 1;
                String bAadhaar = rs.getString("Aadhaar");
                String bfname = rs.getString("fname");
                String bmname = rs.getString("mname");
                String blname = rs.getString("lname");
                String bphnnum = rs.getString("phnnum");
                String bemail = rs.getString("email");
                String bdr_no = rs.getString("dr_no");
                String baddress = rs.getString("address");
                String bstreet = rs.getString("street");
                String bdistrict = rs.getString("district");
                String bcity = rs.getString("city");
                String bzipcode = rs.getString("zipcode");
                String bstate = rs.getString("state");
                String bsqft = rs.getString("sqft");
                String bhouse_type = rs.getString("house_type");
                String brent = rs.getString("rent");
                
            System.out.println(bAadhaar);
            System.out.println(bfname);
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
                                


                                
                
                RequestDispatcher rd = request.getRequestDispatcher("/edithouseDetails.html");  
                rd.forward(request, response); 
                  }
             if ( row == 0) {
                System.out.println("Enter valid Details : "+sql);
                PrintWriter html_out = response.getWriter();
                html_out.print("Sorry Aadhaar_No not Valid!");  
                html_out.print(" ");
                RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
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
    


            