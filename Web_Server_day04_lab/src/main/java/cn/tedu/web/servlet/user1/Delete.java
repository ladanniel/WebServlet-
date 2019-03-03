package cn.tedu.web.servlet.user1;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDAO;
import entity.User;
public class Delete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//ɾ��ָ���û�
			User use = new User();
			int d = Integer.parseInt(request.getParameter("id"));
			use.setId(d);
			new UserDAO().Del(use);
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("error",
					"ϵͳ�쳣�����Ժ�����");
			request.getRequestDispatcher("error.jsp")
			.forward(request, response);
		}
		response.sendRedirect("list");
	}

	
}
