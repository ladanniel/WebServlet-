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
	
	
			//��ȡ�û���������
		   String name = request.getParameter("username"); 
		   String pw = request.getParameter("password");
		   System.out.println(name+":"+pw); 
		   /**
		    * ����dao������ѯ���ݿ��¼�������ƥ�������ļ�¼�����¼�ɹ����ض����û��б������¼ʧ��
		    * ת������¼ҳ�沢��ʾ�û�
		    */
		   UserDAO dao = new UserDAO();
		   try{
		   User user = dao.find(name);
		   if (user!=null && user.getPassword().equals(pw)) {
			response.sendRedirect("list");
		}else{
			request.setAttribute("login_failed", "�û������������");//��
			request.getRequestDispatcher("login.jsp").forward(request, response);//��ȡת������ת��
		}
        } catch (SQLException e) {			
			e.printStackTrace();
			request.setAttribute("error", "ϵͳ��æ���Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	  
		
	}
}
	
	
	
          

