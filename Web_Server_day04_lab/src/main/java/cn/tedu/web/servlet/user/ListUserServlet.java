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
				  * ���ݲ�ѯ�����������ɱ��
				  */
				 
				 /**
				  * ��Ϊservlet ���������ɸ��ӵ�ҳ�棬��������ͨ��ת����jsp����jsp������ҳ��
				  */
				 /**
				  * �����ݵ�request
				  */
				 request.setAttribute("users", users);
				 /**
				  * ���ת����
				  */
				 RequestDispatcher rd = request.getRequestDispatcher("LUS3.jsp");
				 /**
				  * ת��
				  */
				 rd.forward(request, response);
				 
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("ϵͳ��æ�����Ժ�����");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
