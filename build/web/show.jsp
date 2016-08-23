<%-- 

    Document   : show
    Created on : Aug 23, 2016, 8:14:50 PM
    Author     : smile
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <<!DOCTYPE>
<html>
<head>
<title>blog main page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<style type="text/css" media="all">
@import "images/style.css";
</style>
</head>
<body>
<div class="content">
  <div id="header">
    <div class="title"/>
      
      <h1>Recent Post</h1>
    </div>
  </div>
  <div id="main">
      <center> <a href="blogmain.html"> <h1 style="margin-left: 100px;margin-top: 150px; position: absolute;"> Home </h1></a></center>
      <div  ></div>
    <div class="center">
      
        
         <% 


      Connection con=null;
        
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            
            try{
             Class.forName("com.mysql.jdbc.Driver");
       con=   DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","root");
       
         }
         catch(Exception e)
         {
         }
      
         
 try {
           PreparedStatement ptmt=con.prepareStatement("select email ,content from blog order by date desc limit 2");
                ResultSet rs=  ptmt.executeQuery();
                 //out.println(rs);
                while(rs.next())
                {%>
                 <h1> <% out.println( " . "+ rs.getString(1));%> <br><h1>
                  <h2> <% out.println(rs.getString(2));%> <br><h2>
                    
                    
                <%}
            }
            catch(Exception e)
            {}

  %>
        
        
    </div>
    <div class="leftmenu">
      <div class="nav">
       
      </div>
    </div>
  </div>
  <div id="prefooter">
    
   
  </div>
  <div id="footer">
    <div class="padding">  </div>
  </div>
</div>
</body>


    
 
</html>
