package cn.tedu.web.servlet.user1;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import entity.User;
import utils.DBUtils;

public class ListUserServlet extends HttpServlet{
/**重定向：response.sendRedirect(url);
 * 服务器可以通过发送302状态码以及一个location消息头(该消息头的值是一个地址，称之为重定向地址)给浏览器，浏览器收到地址后，会立即向重定向地址发送请求
 * 
 * 重定向的两个特征：
 * 重定向地址是任意的；
 * 
 * 
 * 重定向之后，浏览器地址栏的地址会发生变化；
 * 注（细节）：容器在重定向之前，会先清空response对象上，放的所有数据。
 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs =null;
		
		try {
			 conn = DBUtils.getConn(); 
			 String sql = "select *from t_user";
			 stat =conn.prepareStatement(sql);
			 rs = stat.executeQuery();
			 /**
			  * 依据查询到的数据生成表格
			  */
			  out.println("<table border='1' width=60% cellpadding='0' cellspacing='0'> ");
			  out.println("<tr><td>id</td><td>用户名</td><td>密码</td><td>邮箱</td><td>操作</td></tr>");
			 while(rs.next()){
			      int id=rs.getInt("id");
			      String username = rs.getString("username");
			      String password = rs.getString("password");
			      String email = rs.getString("email");
		    out.println("<tr><td>"+id+"</td><td>"+username+"</td><td>"+password+"</td><td>"+email+"</td><td><a href='del?id="+id+"'>删除</td></tr>");  
			 
			 }
		      out.println("</table>");
			  out.println("<p><a href='user.html'>添加用户</a></p>");
			    rs.close();
				conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("系统繁忙，请稍后重试");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
