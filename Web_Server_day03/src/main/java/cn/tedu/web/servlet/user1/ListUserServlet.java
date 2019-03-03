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
/**�ض���response.sendRedirect(url);
 * ����������ͨ������302״̬���Լ�һ��location��Ϣͷ(����Ϣͷ��ֵ��һ����ַ����֮Ϊ�ض����ַ)���������������յ���ַ�󣬻��������ض����ַ��������
 * 
 * �ض��������������
 * �ض����ַ������ģ�
 * 
 * 
 * �ض���֮���������ַ���ĵ�ַ�ᷢ���仯��
 * ע��ϸ�ڣ����������ض���֮ǰ���������response�����ϣ��ŵ��������ݡ�
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
			  * ���ݲ�ѯ�����������ɱ��
			  */
			  out.println("<table border='1' width=60% cellpadding='0' cellspacing='0'> ");
			  out.println("<tr><td>id</td><td>�û���</td><td>����</td><td>����</td><td>����</td></tr>");
			 while(rs.next()){
			      int id=rs.getInt("id");
			      String username = rs.getString("username");
			      String password = rs.getString("password");
			      String email = rs.getString("email");
		    out.println("<tr><td>"+id+"</td><td>"+username+"</td><td>"+password+"</td><td>"+email+"</td><td><a href='del?id="+id+"'>ɾ��</td></tr>");  
			 
			 }
		      out.println("</table>");
			  out.println("<p><a href='user.html'>����û�</a></p>");
			    rs.close();
				conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("ϵͳ��æ�����Ժ�����");
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
