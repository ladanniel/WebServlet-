package cn.tedu.web.servlet.user1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import entity.User;
public class loginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	
	
			//读取用户名和密码
		   String name = request.getParameter("username"); 
		   String pw = request.getParameter("password");
		   System.out.println(name+":"+pw); 
		   /**
		    * 调用dao方法查询数据库记录，如果有匹配条件的记录，则登录成功，重定向到用户列表，否则登录失败
		    * 转发到登录页面并提示用户
		    */
		   UserDAO dao = new UserDAO();
		   try{
		   User user = dao.find(name);
		   if (user!=null && user.getPassword().equals(pw)) {
			response.sendRedirect("list");
		}else{
			request.setAttribute("login_failed", "用户名或密码错误");//绑定
			request.getRequestDispatcher("login.jsp").forward(request, response);//获取转发器并转发
		}
        } catch (SQLException e) {			
			e.printStackTrace();
			request.setAttribute("error", "系统繁忙，稍后重试");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	  
		
	}
}
	
	
	
          

