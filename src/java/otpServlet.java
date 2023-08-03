import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
import java.util.Scanner;
import javax.servlet.*;  


public class otpServlet extends HttpServlet {

   
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
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     
       


    public void Validation(HttpServletRequest request, HttpServletResponse response){
        String bOTP = request.getParameter("OTP");
       // String bPassword = request.getParameter("Password");
        

        String sql;
        String sotp="";
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            System.out.println(bOTP);
            
           sql =  "Select otp from temp_otp " + "where(otp = '"+bOTP+"')";     
           

            
        //    sql = "Select otp from temp_otp " + "where (otp = '"+bOTP+"');";
           // System.out.println(sql);
            
          System.out.println("bOTP is" + bOTP);
          //String eotp;
         // eotp=bOTP;
        //  System.out.println("eotp is" + eotp);
        
        /*  Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql); 
            PrintWriter html_out = response.getWriter(); 
        */
          
           File Recotp = new File("C:\\Users\\User\\Documents\\ehrs jars\\filename.txt");
           try (Scanner myReader = new Scanner(Recotp))
                {
                        while (myReader.hasNextLine())
                        {
                         sotp = myReader.nextLine();
                         System.out.println("sent otp is "+ sotp);
                        }
                  }       
                
             
           
        catch (FileNotFoundException e)
        {
             System.out.println("An error occurred reading otp.");
             e.printStackTrace();
        }
                
                System.out.println("sent otp after try & block "+ sotp);
          
                 Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
                System.out.println("success : ");
                row = 1;
                
                RequestDispatcher rd = request.getRequestDispatcher("/capturing.html");  
                rd.forward(request, response); 
                  }
             if ( row == 0) {
                System.out.println("Failed : ");
                //PrintWriter html_out = response.getWriter();
                PrintWriter out = response.getWriter(); 
                out.println("<script type=\"text/javascript\">"); 
                out.println("alert('Please Enter Valid UserName and Password');"); 
                out.println("location='login.html';");   
                out.println("</script>"); 
              //  html_out.print("Sorry UserName or Password Error!");  
               // html_out.print(" ");
                //RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
                //rd.include(request, response);
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
    

          

         /*   if(m == 1)
            {
                 System.out.println("sotp inside if "+ sotp);
                  System.out.println("bOTP inside if "+ bOTP);
                System.out.println("You entered into if statement");
             if(bOTP.equals(sotp))
             {
                   
            System.out.println("You entered into  second if statement");
                html_out.print("inserted successfully :   "+sql);
                System.out.println("inserted successfully : "+sql);
                
               System.out.println("You entered correct otp");               
                RequestDispatcher rd;
                rd = request.getRequestDispatcher("/capturing.html");
                rd.forward(request, response);
              }
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
   */
    



        
       /* catch (FileNotFoundException e)
        {
             System.out.println("An error occurred reading otp.");
             e.printStackTrace();
        }*/



       //     Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
           /* ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
               System.out.println(" success 1 : ");

                if(sotp==eotp){
                System.out.println(" success : ");
                row = 1;
                RequestDispatcher rd = request.getRequestDispatcher("/otpvald.html");  
                rd.forward(request, response); 
                  }
            }
             if ( row == 0) {
                System.out.println("Wrong otp : ");
                //PrintWriter html_out = response.getWriter();
                PrintWriter out = response.getWriter(); 
                out.println("<script type=\"text/javascript\">"); 
                out.println("alert('Please Enter Valid OTP');"); 
                out.println("location='login.html';");   
                out.println("</script>"); 
              //  html_out.print("Sorry UserName or Password Error!");  
               // html_out.print(" ");
                //RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
                //rd.include(request, response);
             }
                    
         /*   if (m == 1) 
                System.out.println("Login successfully : "+sql); 
            else
                System.out.println("Login failed");    */
      /*      con.close();
            }catch(Exception e)
                {System.out.println(e);}
    }        
   }*/

    


            