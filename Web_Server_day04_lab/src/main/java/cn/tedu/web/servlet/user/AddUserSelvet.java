package cn.tedu.web.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email   = request.getParameter("email");
        response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	    int pw = Integer.parseInt(password);
		if (username!="") {
            out.println(username);	
		}
		if (password!=null) {
			out.println(password);	
		}
		if (email!="") {
			out.println(email);
		}
		/**
		 * ���û���Ϣ���뵽���ݿ�
		 */
		Connection conn = null;
		PreparedStatement ps=null;
		try {
			conn=DBUtils.getConn();
			String db="insert into t_user values(null,?,?,?)";
			ps=conn.prepareStatement(db);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.execute();
			out.println("<p><a href='list'>�û��б�</a></p>");
			out.println("��ӳɹ�");
		} catch (SQLException e) {
			/**��¼��־�������ֳ���
			 */
			e.printStackTrace();
			/**���쳣�ܷ�ָ���������ָܻ�(�������ݿ������ͣ���������жϵȳ�֮Ϊ��ϵͳ�쳣)��ֻ��Ҫ��ʾ�û��Ժ����ԡ�
			 */
			out.println("ϵͳ��æ�����Ժ�����");
		}finally{
			DBUtils.close(null, ps, conn);
			
		}
		
		out.close();
	}
	  
	

}
