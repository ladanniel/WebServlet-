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
		 * ���߷���ˣ���ζ�����������н���
		 * ע�����д���Ҫ��ӵ����е�getparamenter����ǰ��
		 * ֻ���post������Ч
		 * ע����δ������Ҫ�ӵ�����
		 * URIEncoding="utf-8"
		 * ע��ֻ���get������Ч
		 */

		/**�ȼ���û����Ƿ���ڣ�������ڣ�����ʾ�û��û����Ѿ����ڣ�
		 * ���� ���û���Ϣ���뵽���ݿ�
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		try {
			UserDAO dao=new UserDAO();
			User u1 = dao.find(username);
			if (u1!=null) {//˵���û�����
				request.setAttribute("msg", "�û����Ѿ�����");
				request.getRequestDispatcher("addUser.jsp").forward(request, response);	
			}else{ //�û���Ϊ��
				User u = new User();
				u.setUsername(username);
				u.setPassword(password);
				u.setEmail(request.getParameter("email"));
				dao.Save(u);//������
				/**
				 * �ض����û��б�
				 */
				response.sendRedirect("list");

				/**
				 * ����tcp/ip monitor ץ������
				 */
				System.out.println("�ض���ִ��");
			}
		} catch (Exception e) {
			/**��¼��־�������ֳ���
			 */
			e.printStackTrace();
			/**���쳣�ܷ�ָ���������ָܻ�(�������ݿ������ͣ���������жϵȳ�֮Ϊ��ϵͳ�쳣)��ֻ��Ҫ��ʾ�û��Ժ����ԡ�
			 */
			request.setAttribute("error", "ϵͳ��æ�����Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}
	}



}
