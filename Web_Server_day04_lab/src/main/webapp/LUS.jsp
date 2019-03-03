<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="DAO.*,java.util.*,entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>listUsers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<%@include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						用户列表
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								用户名
							</td>
							<td>
								密  码
							</td>
							<td>
								邮  箱
							</td>
							<td>
								操  作
							</td>
						</tr>
						<% 
						   UserDAO dao  = new UserDAO();
						 List<User> user = dao.findall();
						 for(int i=0;i<user.size();i++){
							 User u = user.get(i);
							 
							 %>
							<tr class="row<%= i % 2 +1 %>">
							<td>
								<%=u.getId() %>
							</td>
							<td>
								<%=u.getUsername() %>
							</td>
							<td>
								<%=u.getPassword() %>
							</td>
							<td>
								<%=u.getEmail() %>
							</td>
							<td>
								<a href="list.html">delete</a>&nbsp;
							</td>
						</tr>
							 <%
						 }
						%>
					</table>
					<p>
						<input type="button" class="button" value="添加用户" onclick="addUser.html"/>
					</p>
				</div>
			</div>
			<%@include file="footer.jsp" %>
			
			
			
			
			
			
			
		</div>
	</body>
</html>
