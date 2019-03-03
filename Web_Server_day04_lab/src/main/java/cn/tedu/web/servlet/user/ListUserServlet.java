package cn.tedu.web.servlet.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
				 
				 /**
				  * 因为servlet 不便于生成复杂的页面，所以我们通常转发给jsp，由jsp来生成页面
				  */
				 /**
				  * 绑定数据到request
				  */
				 request.setAttribute("users", users);
				 /**
				  * 获得转发器
				  */
				 RequestDispatcher rd = request.getRequestDispatcher("LUS3.jsp");
				 /**
				  * 转发
				  */
				 rd.forward(request, response);
				 
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("系统繁忙，请稍后重试");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
