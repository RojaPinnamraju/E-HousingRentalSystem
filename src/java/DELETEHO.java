

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Remove")
public class DELETEHO extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String bAadhaar = request.getParameter("AadhaarNo");

    try {
        Class.forName("com.mysql.jdbc.Driver");
        String user = "uehrs";
        String pass = "pehrs";
        String query = "DELETE * FROM house_details" +  "where (Aadhaar = '"+ bAadhaar +"');";
       
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
        PreparedStatement ps = con.prepareStatement("DELETE FROM house_details WHERE Aadhaar = '"+bAadhaar+"';");
        ps.setString(1, bAadhaar);

        int i = ps.executeUpdate();

        if(i > 0) {
            out.println("User successfully removed...");
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}

}