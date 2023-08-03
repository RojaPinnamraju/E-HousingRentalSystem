
import java.lang.Exception;
import java.io.PrintWriter;      
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintStream;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class otp_ValidateServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        System.out.println("Inside Processes request");
        otpValidate(request,response);
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(otp_ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(otp_ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(otp_ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(otp_ValidateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html");  
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void otpValidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        String otp=getOtp();
        Scanner in=new Scanner(System.in);

        String mailid="";
        String sql;
        Connection con = null;
        
         try
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");  
            System.out.println("Before Connection");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehrs","uehrs","pehrs");
            System.out.println("After Connection");

            sql = "delete from temp_otp";
            Statement st = con.createStatement(); 
            int m = st.executeUpdate(sql);
            
            sql =  "insert into temp_otp values('"+otp+"')"; 
            st = con.createStatement(); 
            m = st.executeUpdate(sql);  
            if (m == 1) {        
                System.out.println("inserted successfully : "+sql);
                          }
            else
            {
                    System.out.println("insertion failed"); 
                }
                
            con.close();
            }catch(Exception e)
                {System.out.println(e);}
      
        try
        {
                File RecMail = new File("C:\\Users\\User\\Documents\\ehrs jars\\tempemail.txt");
                try (Scanner myReader = new Scanner(RecMail)) {
                    while (myReader.hasNextLine()) {
                        mailid = myReader.nextLine();
                        System.out.println(mailid);  
                        sendingMail(from,mailid,otp);
                        RequestDispatcher rd = request.getRequestDispatcher("/otpvald.html");  
                        rd.forward(request, response);                    
                    }
                    
                 //   System.out.println(mailid);
     	//final String to=in.nextLine(); //receiver email
    	            
                }      
                
        }
        catch (FileNotFoundException e)
        {
             System.out.println("An error occurred reading otp.");
             e.printStackTrace();
        }
        
        
    }        
    
    private static String getOtp() {
		Random rn=new Random();
		
		 int[] otp_arr=rn.ints(6, 1, 9).toArray();
		 StringBuilder sb=new StringBuilder();
		 for(int ele:otp_arr)
		 {
			 sb.append(ele);
		 }
		return  sb.toString();
	
		
	}

	private static void sendingMail(final String from,final String mailid, String otp) {
		
		String host="smtp.gmail.com";
		Properties properties =System.getProperties();
		Map<String,String> map=getPropertiesMap(host,properties);
		properties.putAll(map);
		Session secn=Session.getInstance(properties,new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
                               // System.out.println("Enter Password :-");
                               // Scanner in=new Scanner(System.in);
                                //final String password=in.nextLine();
                            String password="9052309035";
				return new PasswordAuthentication(from,password);//sender password
				
			}
			
			});
	
		
		secn.setDebug(true);
		MimeMessage mim=new MimeMessage(secn);
		try
		{
                        
                        
			mim.setFrom(from);
			mim.addRecipient(Message.RecipientType.TO, new InternetAddress(mailid));
			
                        
                        
                        try {
                             FileWriter myWriter = new FileWriter("C:\\Users\\User\\Documents\\ehrs jars\\tempemail.txt");
                             myWriter.write(otp);
                             myWriter.close();
                             System.out.println("Successfully wrote to the file.");
                           } catch (IOException e) {
                             System.out.println("An error occurred.");
                             e.printStackTrace();
                            }
        
                        
			mim.setSubject("Important:- Otp recieved ");
			
			mim.setText("This is your otp "+otp+" dont share with anyone");
		
		
			Transport.send(mim);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
            System.out.println("this is otp :- "+otp);
                System.out.println("done..");
	}

	private static Map<String, String> getPropertiesMap(String host,Properties properties) {
		
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put("mail.smtp.host", host);
		map.put("mail.smtp.port", "465");
		map.put("mail.smtp.ssl.enable", "true");
		map.put("mail.smtp.auth", "true");
		return map;
	}
    
   }
    
