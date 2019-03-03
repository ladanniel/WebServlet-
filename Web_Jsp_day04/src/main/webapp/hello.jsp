<%@ page import="java.util.*,java.text.*"%>
<html>
    <head>
         <body style="font-size: 30px;">
         time:<%=new Date() %>>
         
         
         time:
             <% 
               Date date = new Date();
               out.println(date);
             %>
             <br/>
           <%
             for(int i=0;i<=100;i++){
            	 out.println("hello kitty<br/>");
             } 
           %>
         </body>
    </head>
</html>