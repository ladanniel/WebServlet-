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

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 3L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		try {
			UserDAO dao = new UserDAO();
			User user = new User();
			user.setId(Integer.parseInt(request.getParameter("id")));
			dao.Del(user);
			//ɾ����ϣ��������Ҫ�ض���list���ض���url�ϣ�ת����ת�����ļ���
			response.sendRedirect("ListUser");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "ϵͳ��æ�����Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
	}

}
