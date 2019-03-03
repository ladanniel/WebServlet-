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
		request.setCharacterEncoding("utf-8");

		/**request.setCharacterEncoding("utf-8");  
		 * 
		 * 告诉服务端，如何对请求参数进行解码
		 * 注：这行代码要添加到所有的getparamenter方法前面
		 * 只针对post请求有效
		 * 注：这段代码必须要加到首行
		 * URIEncoding="utf-8"
		 * 注：只针对get请求有效
		 */

		/**先检查用户名是否存在，如果存在，则提示用户用户名已经存在；
		 * 否则将 将用户信息插入到数据库
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		try {
			UserDAO dao=new UserDAO();
			User u1 = dao.find(username);
			if (u1!=null) {//说明用户存在
				request.setAttribute("msg", "用户名已经存在");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);	
			}else{ //用户名为空
				User u = new User();
				u.setUsername(username);
				u.setPassword(password);
				u.setEmail(request.getParameter("email"));
				dao.Save(u);//调参数
				/**
				 * 重定向到用户列表
				 */
				response.sendRedirect("list");

				/**
				 * 启动tcp/ip monitor 抓包工具
				 */
				System.out.println("重定向执行");
			}
		} catch (Exception e) {
			/**记录日志（保留现场）
			 */
			e.printStackTrace();
			/**看异常能否恢复，如果不能恢复(比如数据库服务暂停或者网络中断等称之为：系统异常)，只需要提示用户稍后重试。
			 */
			request.setAttribute("error", "系统繁忙，请稍后重试");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}
	}



}
