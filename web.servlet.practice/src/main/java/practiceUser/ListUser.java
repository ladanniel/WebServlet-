package practiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import entity.User;

/**
 * Servlet implementation class ListUser
 */
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 2L;

	
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
			List<User> users = dao.findall();
			request.setAttribute("users", users);
			RequestDispatcher rd = request.getRequestDispatcher("listUser2.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "系统繁忙，请稍后再试");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
