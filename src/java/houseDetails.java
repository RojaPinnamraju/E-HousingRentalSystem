  
import java.lang.Exception;
import java.io.PrintWriter;      
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;




public class houseDetails extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside Processes request");
        Validation(request,response);
        
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

    public void Validation(HttpServletRequest request, HttpServletResponse response){
        String bAadhaarNo = request.getParameter("AadhaarNo");
        String bFirstName = request.getParameter("FirstName");
        String bMiddleName = request.getParameter("MiddleName");
        String bLastName = request.getParameter("LastName");
        String bPhoneNo = request.getParameter("PhoneNo");
        String bEmail = request.getParameter("Email");
        String bDr_No = request.getParameter("Dr_No");
        String bAddress = request.getParameter("Address");
        String bStreet = request.getParameter("Street");
        String bDistrict = request.getParameter("District");
        String bCity = request.getParameter("City");
        String bZipcode = request.getParameter("Zipcode");
        String bState = request.getParameter("State");
        String bSqft = request.getParameter("Sqft");
        String bHouseType = request.getParameter("HouseType");
        String bApproxRent = request.getParameter("ApproxRent");
        
        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            
           sql =  "insert into house_details values('"+bAadhaarNo+"','"+bFirstName+"','"+bMiddleName+"','"+bLastName+"','"+bPhoneNo+"','"+bEmail+"','"+bDr_No+"','"+bAddress+"','"+bStreet+"','"+bDistrict+"','"+bCity+"','"+bZipcode+"','"+bState+"','"+bSqft+"','"+bHouseType+"','"+bApproxRent+"')";
           
           
           
           Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql); 
            PrintWriter html_out = response.getWriter();  
            if (m == 1) {
                html_out.print("inserted successfully :   "+sql);
                System.out.println("inserted successfully : "+sql);
                RequestDispatcher rd=request.getRequestDispatcher("/owner.html");
                rd.forward(request, response);
                          }
            else
                {
                    html_out.print("insertion Failed : ");
                    System.out.println("insertion failed"); 
                    RequestDispatcher rd=request.getRequestDispatcher("/index.html");
                    rd.forward(request, response);
                }
                
            con.close();
            }catch(Exception e)
                {System.out.println(e);}
    }        
   }
    
