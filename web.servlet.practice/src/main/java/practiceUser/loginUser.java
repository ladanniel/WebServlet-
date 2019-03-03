package practiceUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import entity.User;


public class loginUser extends HttpServlet {
	private static final long serialVersionUID = 4L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pw = request.getParameter("password");
		UserDAO dao = new UserDAO();
		try {
			User user = dao.find(uname);
			if (uname!=null && user.getPassword().equals(pw)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("ListUser");
			}else{
				out.println("�û����������������������");
				request.setAttribute("login_failed", "�û������������");//��
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "ϵͳ��æ�����Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
