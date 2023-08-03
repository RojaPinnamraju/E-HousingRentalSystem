/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class bufferServlet extends HttpServlet {
    

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
        String bOTP = request.getParameter("OTP");
      
        
        String sql;
        String sotp="";
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            sql =  "insert into temp_otp values('"+bOTP+"')";     
            File Recotp = new File("C:\\Users\\LENOVO\\Desktop\\filename.txt");
                try (Scanner myReader = new Scanner(Recotp))
                {
                        while (myReader.hasNextLine())
                        {
                         sotp = myReader.nextLine();
                         System.out.println("sent otp is "+ sotp);
                        }
                         
                }
                System.out.println("sent otp is 2 "+ sotp);

        
       /* catch (FileNotFoundException e)
        {
             System.out.println("An error occurred reading otp.");
             e.printStackTrace();
        }*/
            
            Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql); 
            PrintWriter html_out = response.getWriter();  
            String eotp=bOTP;
                            System.out.println("Entered otp is  "+ eotp);

            if (m == 1 && sotp == eotp)
            {
                //html_out.print("inserted successfully :   "+sql);
                //System.out.println("inserted successfully : "+sql);
                
               System.out.println("You entered correct otp");               
                RequestDispatcher rd;
                rd = request.getRequestDispatcher("/capturing.html");
                rd.forward(request, response);
            }
            else
            {
                    html_out.print("insertion Failed : ");
                    //System.out.println("insertion failed"); 
                    //PrintWriter html_out = response.getWriter();
                    PrintWriter out = response.getWriter(); 
                    out.println("<script type=\"text/javascript\">"); 
                    out.println("alert('Please Enter valid otp');"); 
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
    

