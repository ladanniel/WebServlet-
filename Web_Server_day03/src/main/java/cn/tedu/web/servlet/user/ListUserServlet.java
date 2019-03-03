package cn.tedu.web.servlet.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import entity.User;
import utils.DBUtils;

public class ListUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		  
		
		try {
			  UserDAO dao = new UserDAO();
			  List<User> users = dao.findall();
			 /**
			  * 依据查询到的数据生成表格
			  */
			  out.println("<table border='1' width=60% cellpadding='0' cellspacing='0'> ");
			  out.println("<tr><td>id</td><td>用户名</td><td>密码</td><td>邮箱</td></tr>");
			 for (User u:users) {
				int id = u.getId();
				String username = u.getUsername();
				String password = u.getPassword();
				String email = u.getEmail();
		    out.println("<tr><td>"+id+"</td><td>"+username+"</td><td>"+password+"</td><td>"+email+"</td></tr>");  
			 
			 }
		      out.println("</table>");
			  out.println("<p><a href='user.html'>添加用户</a></p>");
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("系统繁忙，请稍后重试");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
