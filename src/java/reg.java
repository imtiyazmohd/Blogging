/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Class.forName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smile
 */
public class reg extends HttpServlet {

    

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection con=null;
      
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
         String username=request.getParameter("username");
         String email=request.getParameter("email");
         String pass=request.getParameter("pass");
         String add=request.getParameter("address");
         String cpass=request.getParameter("cpass");
         //out.println(pass + cpass);
         if(username.equals("")||email.equals("")||pass.equals("")||add.equals(""))
         {
             
             response.setContentType("text/html;charset=UTF-8");
        
  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
        RequestDispatcher dist;
                        dist = request.getRequestDispatcher("index.html");
                       out.println("<h3>------------------------Null value not allowed please enter all detail------------------------- <h3>");
           dist.include(request, response);
         }
         else if(!pass.equals(cpass))
         {
              response.setContentType("text/html;charset=UTF-8");
        
  
            out.println("<!DOCTYPE html>");
            out.println("<html>");
        RequestDispatcher dist;
                        dist = request.getRequestDispatcher("index.html");
                       out.println("<h3>-------------- Password should be same------------- <h3>");
           dist.include(request, response);
             
         }
         
         else{
         //out.println(username + email + pass+add);
         try{
             Class.forName("com.mysql.jdbc.Driver");
       con=   DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
       
         }
         catch(Exception e)
         {
         }
         
            try {
           PreparedStatement ptmt=con.prepareStatement("insert into reg values(?,?,?,?)");
          ptmt.setString(1,username);
           ptmt.setString(2,email);
           ptmt.setString(3,pass);
           ptmt.setString(4,add);
         
            int i=ptmt.executeUpdate();  
             if(i>=0){
  HttpSession session=request.getSession();  
        session.setAttribute("email",email);  
        session.setAttribute("pass", pass);  
//out.println(i+" records inserted"); 
response.sendRedirect("blogmain.html");
             }
            } catch (SQLException ex) {
                Logger.getLogger(reg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    }
   

}
