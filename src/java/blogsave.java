/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smile
 */
@WebServlet(urlPatterns = {"/blogsave"})
public class blogsave extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection con=null;
        
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session=request.getSession();  
            String email = (String)session.getAttribute("email");
            String pass=(String)session.getAttribute("pass");
            
           
            String content =request.getParameter("content"); 
            //out.println(email+"   " +  pass + "   "+ content);
            
            
            
            
             try{
             Class.forName("com.mysql.jdbc.Driver");

             con=   DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
         // out.println(con);
         }
         catch(Exception e)
         {
         }
            out.println(con);
 //out.println("wrong email or password!!!1");
        try {

            PreparedStatement ptmt=con.prepareStatement("insert into blog values(?,?,now())");
           ptmt.setString(1, email);
           ptmt.setString(2, content);
           // ptmt.setString();
          int i=ptmt.executeUpdate();  
             if(i>=0){
          response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
        RequestDispatcher dist;
                        dist = request.getRequestDispatcher("show.jsp");
                     //  out.println("<h3><h3>");
           dist.include(request, response);
             }
           
        }
        catch(Exception e)
        {}
    }

    }} 


