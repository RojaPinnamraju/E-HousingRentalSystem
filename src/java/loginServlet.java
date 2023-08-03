
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
import javax.servlet.*;  


public class loginServlet extends HttpServlet {

   
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
        String bEmail = request.getParameter("Email");
        String bPassword = request.getParameter("Password");
        

        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            System.out.println(bEmail);
            System.out.println(bPassword);
            
            sql = "Select email,password from registration " + "where (email = '"+bEmail + "' and password = '"+bPassword + "');";
            System.out.println(sql);
             try {
                             FileWriter myWriter = new FileWriter("C:\\Users\\User\\Documents\\ehrs jars\\Tempemail.txt");
                             myWriter.write(bEmail);
                             myWriter.close();
                             System.out.println("Successfully wrote to the file.");
            } 
            catch (IOException e) 
            {
                             System.out.println("An error occurred.");
                             e.printStackTrace();
              }
            
            

            Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
                System.out.println("Login successfully : ");
                row = 1;
                response.sendRedirect("otp_ValidateServlet");
                //RequestDispatcher rd = request.getRequestDispatcher("/otpvald.html");  
                //rd.forward(request, response); 
                  }
             if ( row == 0) {
                System.out.println("Login Unsuccessfully : ");
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
    


            