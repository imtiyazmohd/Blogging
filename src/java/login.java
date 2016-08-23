/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.rmi.server.Dispatcher;

/**
 *
 * @author smile
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class login extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con=null;
        PrintWriter out=response.getWriter();
        String pass=request.getParameter("password");
        String email=request.getParameter("email");
       // email="vishwanand505@gmail.com";
        //pass="123456";
        
        if(pass.equals("") || email.equals(""))
        {
        response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
        RequestDispatcher dist;
                        dist = request.getRequestDispatcher("index.html");
                       out.println("<h3>Fill the Field marked with *<h3>");
           dist.include(request, response);
        
        }
        else{
        
          try{
             Class.forName("com.mysql.jdbc.Driver");
       con=   DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
         // out.println(con);
         }
         catch(Exception e)
         {
         }
          
 //out.println("wrong email or password!!!1");
        try {
            PreparedStatement ptmt=con.prepareStatement("select pass, email from reg where email=?");
           ptmt.setString(1, email);
            ResultSet rs=ptmt.executeQuery();
             //out.println(rs.getString(1));
            while(rs.next()){  
                
                    if(rs.getString(1).equals(pass))
            {
                 response.setContentType("text/html;charset=UTF-8");
                 HttpSession session=request.getSession();  
        session.setAttribute("email",email);  
        session.setAttribute("pass", pass);
                 
                
                  out.println("<!DOCTYPE html>");
               RequestDispatcher dist;
                        dist = request.getRequestDispatcher("blogmain.html");
                     
           dist.include(request, response);
            }
            else{
                           response.setContentType("text/html;charset=UTF-8");
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
        RequestDispatcher dist;
                        dist = request.getRequestDispatcher("index.html");
                       out.println("<h3>-------wrong password or Email!!!!!<h3>");
           dist.include(request, response);
        
                    
                    }  
                          }  
        
           
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
          
    }

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      doPost( request, response);
        PrintWriter out=response.getWriter();
        out.println("inside get");
    }
    
}
