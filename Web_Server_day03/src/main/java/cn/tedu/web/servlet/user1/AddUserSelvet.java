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
		 * ���߷���ˣ���ζ�����������н���
		 * ע�����д���Ҫ��ӵ����е�getparamenter����ǰ��
		 * ֻ���post������Ч
		 * ע����δ������Ҫ�ӵ�����
		 * URIEncoding="utf-8"
		 * ע��ֻ���get������Ч
		 */
	    response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
       
		/**
		 * ���û���Ϣ���뵽���ݿ�
		 */
//		Connection conn = null;
//		PreparedStatement ps=null;
		try {
			User u = new User();
			u.setUsername(request.getParameter("username"));
		    u.setPassword(request.getParameter("password"));
			u.setEmail(request.getParameter("email"));
			new UserDAO().Save(u);//������
					
//			conn=DBUtils.getConn();
//			String db="insert into t_user values(null,?,?,?)";
//			ps=conn.prepareStatement(db);
//			ps.setString(1, u.getUsername());
//			ps.setString(2, u.getPassword());
//			ps.setString(3, u.getEmail());
//			ps.execute();
			/**
			 * �ض����û��б�
			 */
			response.sendRedirect("list");
			response.sendRedirect("delete");
			/**
			 * ����tcp/ip monitor ץ������
			 */
			System.out.println("�ض���ִ��");
		} catch (Exception e) {
			/**��¼��־�������ֳ���
			 */
			e.printStackTrace();
			/**���쳣�ܷ�ָ���������ָܻ�(�������ݿ������ͣ���������жϵȳ�֮Ϊ��ϵͳ�쳣)��ֻ��Ҫ��ʾ�û��Ժ����ԡ�
			 */
			out.println("ϵͳ��æ�����Ժ�����");
		}
		
		
		out.close();
	}
	  
	

}
