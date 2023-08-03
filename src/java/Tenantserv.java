
import java.lang.Exception;
import java.io.PrintWriter;      
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

public class Tenantserv extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside Processes request");
        registration(request,response);
        
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
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void registration(HttpServletRequest request, HttpServletResponse response){
        String bFirstName = request.getParameter("FirstName");
        String bMiddleName  = request.getParameter("MiddleName");  
        String bLastName = request.getParameter("LastName");
        String bAadhaarNo  = request.getParameter("AadhaarNo"); 
        String bMobileNo = request.getParameter("MobileNo");
        String bDob  = request.getParameter("Dob"); 
        String bmail = request.getParameter("Email");
        String bEmail  = request.getParameter("Address");  
        String bDr_no=request.getParameter("Dr_no");
        String bZipcode  = request.getParameter("Zipcode");
        
        String bDistrict  = request.getParameter("District");
        String bState  = request.getParameter("State");
        String bFamilySize  = request.getParameter("FamilySize");
        String bApproxRent  = request.getParameter("ApproxRent");
        String bHouseType  = request.getParameter("HouseType");
         
        
         
         
        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            sql =  "insert into tenant_details values('"+bFirstName+"','"+bMiddleName+"','"+bLastName+"','"+bAadhaarNo+"','"+bMobileNo+"','"+bDob+"','"+bmail+"','"+bEmail+"','"+bDr_no+"','"+bZipcode+"','"+bDistrict+"','"+bState+"','"+bFamilySize+"','"+bApproxRent+"','"+bHouseType+"')";
            Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql); 
            PrintWriter html_out = response.getWriter();  
            if (m == 1) {
                html_out.print("inserted successfully :   "+sql);
                System.out.println("inserted successfully : "+sql);
                RequestDispatcher rd = request.getRequestDispatcher("./tenent_req.html");  
                rd.forward(request, response); 
                          }
            else
                {
                    html_out.print("insertion Failed : ");
                    System.out.println("insertion failed"); 
                    
                    RequestDispatcher rd=request.getRequestDispatcher("./tenent.html");  
                    rd.include(request, response);
                }
                
            con.close();
            }catch(Exception e)
                {System.out.println(e);}
    }        
   }
    
