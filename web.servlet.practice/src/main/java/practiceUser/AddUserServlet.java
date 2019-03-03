package practiceUser;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import entity.User;
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String username  = request.getParameter("username");
	String password = request.getParameter("password");
	System.out.println(username);
	System.out.println(password);
	//session��֤
	    HttpSession session = request.getSession();
	    Object obj = session.getAttribute("user");
	    if (obj==null) {
			response.sendRedirect("login.jsp");
			return;//�˴����û��return��������������ִ��
		}
	    
	try {
		UserDAO dao = new UserDAO();
		User u = dao.find(username);
		if (u!=null) {
			//����û����ظ����ð�
			request.setAttribute("rname", "���û����Ѿ����ڣ�����������");
			request.getRequestDispatcher("addUser.jsp").forward(request, response);
			
		}else{
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(request.getParameter("email"));
			dao.Save(user);
			//�������Ľ�����ض��򵽽��ҳ��
			response.sendRedirect("ListUser");
			System.out.println("ִ���ض��򡢡�����");
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		request.setAttribute("error", "ϵͳ��æ�����Ժ�����");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}	
	}
}
