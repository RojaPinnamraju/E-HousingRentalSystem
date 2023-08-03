/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otp_gen;


   
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.mavenproject1;

    

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
//import java.net.Authenticator;
//import javax.websocket.Session;
/**
 * sending mail
 *
 */
public class java
{
    public static void main( String[] args )
    {
    	String otp=getOtp();
        Scanner in=new Scanner(System.in);
        //System.out.println("Enter sender mail :- ");
    	//final String from=in.nextLine(); //sender email
        //System.out.println("Enter Receiver mail :- ");
        String mailid="";
        try
        {
                File RecMail = new File("C:\\Users\\User\\Documents\\ehrs jars\\tempemail.txt");
                try (Scanner myReader = new Scanner(RecMail)) {
                    while (myReader.hasNextLine()) {
                        mailid = myReader.nextLine();
                        System.out.println(mailid);
                    }
                }
        }
        catch (FileNotFoundException e)
        {
             System.out.println("An error occurred reading otp.");
             e.printStackTrace();
        }
        System.out.println(mailid);
     	//final String to=in.nextLine(); //receiver email
    	sendingMail(from,mailid,otp);
      
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
                             FileWriter myWriter = new FileWriter("C:\\Users\\User\\Documents\\ehrs jars\\filename.txt");
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

/*
 * 
 * <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.mywrk</groupId>
  <artifactId>send_rnd_otp</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>send_rnd_otp</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
  <!-- https://mvnrepository.com/artifact/com.sun.mail/javax.mail -->
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>

 * 
 * 
 * 
 * 
 * */

