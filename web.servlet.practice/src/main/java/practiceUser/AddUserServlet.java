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
	//session认证
	    HttpSession session = request.getSession();
	    Object obj = session.getAttribute("user");
	    if (obj==null) {
			response.sendRedirect("login.jsp");
			return;//此处如果没有return，程序会继续往下执行
		}
	    
	try {
		UserDAO dao = new UserDAO();
		User u = dao.find(username);
		if (u!=null) {
			//如果用户名重复设置绑定
			request.setAttribute("rname", "该用户名已经存在，请重新输入");
			request.getRequestDispatcher("addUser.jsp").forward(request, response);
			
		}else{
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(request.getParameter("email"));
			dao.Save(user);
			//看创建的结果，重定向到结果页面
			response.sendRedirect("ListUser");
			System.out.println("执行重定向、、、、");
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		request.setAttribute("error", "系统繁忙，请稍后再试");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}	
	}
}
