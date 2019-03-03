package cn.tedu.web.servlet.user1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import entity.User;
import utils.DBUtils;
public class AddUserSelvet extends HttpServlet   {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		/**request.setCharacterEncoding("utf-8");
		 * 告诉服务端，如何对请求参数进行解码
		 * 注：这行代码要添加到所有的getparamenter方法前面
		 * 只针对post请求有效
		 * 注：这段代码必须要加到首行
		 * URIEncoding="utf-8"
		 * 注：只针对get请求有效
		 */
	    response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
       
		/**
		 * 将用户信息插入到数据库
		 */
//		Connection conn = null;
//		PreparedStatement ps=null;
		try {
			User u = new User();
			u.setUsername(request.getParameter("username"));
		    u.setPassword(request.getParameter("password"));
			u.setEmail(request.getParameter("email"));
			new UserDAO().Save(u);//调参数
					
//			conn=DBUtils.getConn();
//			String db="insert into t_user values(null,?,?,?)";
//			ps=conn.prepareStatement(db);
//			ps.setString(1, u.getUsername());
//			ps.setString(2, u.getPassword());
//			ps.setString(3, u.getEmail());
//			ps.execute();
			/**
			 * 重定向到用户列表
			 */
			response.sendRedirect("list");
			response.sendRedirect("delete");
			/**
			 * 启动tcp/ip monitor 抓包工具
			 */
			System.out.println("重定向执行");
		} catch (Exception e) {
			/**记录日志（保留现场）
			 */
			e.printStackTrace();
			/**看异常能否恢复，如果不能恢复(比如数据库服务暂停或者网络中断等称之为：系统异常)，只需要提示用户稍后重试。
			 */
			out.println("系统繁忙，请稍后重试");
		}
		
		
		out.close();
	}
	  
	

}
