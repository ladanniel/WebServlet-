<%@page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="java.util.*,DAO.*,entity.*" %>
<html>
     <head>
           <style type="text/css">
                .row1{
                     background-color: red;
                     text-align: center;
                }
                .row2{ background-color: pink;
                text-align: center;
                }
                
                table {  border-collapse:collapse;
                        border-width: 1px;
                        border: 1px solid red;
	                    text-align: center;
	                     width: 500px;
	                     height: 600px;
	                     border: 1px red;
}
           
           </style>
     
     
           <body style="font-size: 30px;">
          
                 <table border="1" cellpadding="1" cellspacing="1">
                    <tr>
                        <td>id</td>
                        <td>用户名</td>
                        <td>密   码</td>
                        <td>邮   箱</td>
                  </tr>
                  <% 
                   UserDAO dao = new UserDAO();
                  List<User> user = dao.findall();
                   for(int i=0;i<user.size();i++){
                	   User u = user.get(i);
                	   %>
                	   <tr class="row<%=i % 2 + 1 %>">
                	       <td><%=u.getId() %></td>
                	       <td><%=u.getUsername() %></td>
                	       <td><%=u.getPassword() %></td>
                	       <td><%=u.getEmail() %></td>
                	   </tr>
                	   <%
                   }
                  
                  %>
                 
                 </table>
                
           
           </body>
     </head>

</html>