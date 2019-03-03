package cn.tedu.web.servlet.user1;
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
		try {
			UserDAO dao = new UserDAO();
			  List<User> users = dao.findall();
			
				
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
		/**ת����ϵͳ�쳣����ҳ��
		 * 
		 */
			request.setAttribute("error", "ϵͳ��æ�����Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
	}
       
}
