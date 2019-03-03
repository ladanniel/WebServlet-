<%@page import="DAO.*,java.util.*,entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<html>
      <head>
            <style type="text/css">
              .row1{
              background-color: gray;
              }
              .row2{
              background-color: pink;}
              
               table {  border-collapse:collapse;
                        border-width: 1px;
                        border: 1px solid red;
	                    text-align: center;
	                     width: 500px;
	                     height: 50px;
	                     border: 1px red;
	                     margin: 100px 0px 300px 300px;
}              h1{
                  margin: 100px 0px -100px 500px;
                  font-size: 20px;
}
            </style>
              
            <body>
                  <table border="1" cellpadding="1" cellspacing="1" width="50%">
                           <h1>用户列表</h1>
                        <tr>
                            <td>id</td>
                            <td>用户名</td>
                            <td>密码</td>
                            <td>邮箱</td>
                        </tr>
                          <%
                             UserDAO dao = new UserDAO();
                             List<User> list = dao.findall();
                             for(int i=0;i<list.size();i++){
                            	 User use = list.get(i);
                            	 %>
                            	 <tr class="row<%=i % 2 +1 %>">
                            	    <td><%=use.getId() %></td>
                            	    <td><%=use.getUsername() %></td>
                            	    <td><%=use.getPassword() %></td>
                            	    <td><%=use.getEmail() %></td>
                            	 </tr>
                            	 
                            	 <%
                             }
                          %>
                  </table>
            </body>
      </head>
</html>