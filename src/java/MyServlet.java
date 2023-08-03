
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


public class MyServlet extends HttpServlet {

   
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
        String fuser = request.getParameter("user");
        String fpass  = request.getParameter("pass");   
        String sql;
        Connection con = null;
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");
            sql = "Select user,pass from login " + "where (user = '"+fuser + "' and pass = '"+fpass + "');";
            System.out.println(sql);
            Statement st = con.createStatement(); 
           /* int m = st.executeUpdate(sql); */
            ResultSet rs = st.executeQuery(sql);
            int row = 0;
            if (rs.next()) {
                System.out.println("Login successfully : "+sql);
                row = 1;
                RequestDispatcher rd = request.getRequestDispatcher("/registration.html");  
                rd.forward(request, response); 
                  }
             if ( row == 0) {
                System.out.println("Login Unsuccessfully : "+sql);
                PrintWriter html_out = response.getWriter();
                html_out.print("Sorry UserName or Password Error!");  
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
    


            