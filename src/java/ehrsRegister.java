/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class ehrsRegister extends HttpServlet {
    

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inside Processes request");
        register(request,response);
        
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

    public void register(HttpServletRequest request, HttpServletResponse response){
        String bFirstName = request.getParameter("FirstName");
        String bMiddleName  = request.getParameter("MiddleName");  
        String bLastName = request.getParameter("LastName");
        String bAadharNo  = request.getParameter("AadharNo"); 
        String bDate_Of_Reg = request.getParameter("Date_Of_Reg");
        String bD_O_B  = request.getParameter("D_O_B"); 
        String bMobileNo = request.getParameter("MobileNo");
        
      //String mNO2  = request.getParameter("MobileNo_2");
        
        String bEmail = request.getParameter("Email");
        String bAddress_Line1  = request.getParameter("Address_Line1");
        String bAddress_Line2  = request.getParameter("Address_Line2");
        String bState  = request.getParameter("State");
        String bDistrict  = request.getParameter("District");
        String bZipcode  = request.getParameter("Zipcode");
        
        String bUserName  = request.getParameter("UserName");
        String bPassword  = request.getParameter("Password");
        String bConfirm_Pass  = request.getParameter("Confirm_Pass");
        
        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            sql =  "insert into registration values('"+bFirstName+"','"+bMiddleName+"','"+bLastName+"','"+bAadharNo+"','"+bDate_Of_Reg+"','"+bD_O_B+"','"+bMobileNo+"','"+bEmail+"','"+bAddress_Line1+"','"+bAddress_Line2+"','"+bState+"','"+bDistrict+"','"+bZipcode+"','"+bUserName+"','"+bPassword+"','"+bConfirm_Pass+"')";
            
            
            Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql); 
            PrintWriter html_out = response.getWriter();  
            if (m == 1) {
                html_out.print("inserted successfully :   "+sql);
                System.out.println("inserted successfully : "+sql);
                RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                rd.forward(request, response);
                          }
            else
                {
                    html_out.print("insertion Failed : ");
                    //System.out.println("insertion failed"); 
                    //PrintWriter html_out = response.getWriter();
                    PrintWriter out = response.getWriter(); 
                    out.println("<script type=\"text/javascript\">"); 
                    out.println("alert('Please Enter complete details');"); 
                    out.println("location='register.html';");   
                    out.println("</script>"); 
                    //RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                    //rd.forward(request, response);
                }
                
            con.close();
            }catch(Exception e)
                {System.out.println(e);}
    }        
   }
    

