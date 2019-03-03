package cn.tedu.web.servlet.user;
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
import utils.DBUtils;

public class ListUserServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Connection conn;
		PreparedStatement stat;
		ResultSet rs;
		
		try {
			 conn = DBUtils.getConn(); 
			 String sql = "select *from t_user";
			 stat =conn.prepareStatement(sql);
			 rs = stat.executeQuery();
			 /**
			  * 依据查询到的数据生成表格
			  */
			  out.println("<table border='1' width=60% cellpadding='0' cellspacing='0'> ");
			  out.println("<tr><td>id</td><td>用户名</td><td>密码</td><td>邮箱</td></tr>");
			 while(rs.next()){
			      int id=rs.getInt("id");
			      String username = rs.getString("username");
			      String password = rs.getString("password");
			      String email = rs.getString("email");
		    out.println("<tr><td>"+id+"</td><td>"+username+"</td><td>"+password+"</td><td>"+email+"</td></tr>");  
			 
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
