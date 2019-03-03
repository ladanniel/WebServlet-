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

import utils.DBUtils;

public class Delete extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		System.out.println(id);
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "DELETE FROM t_user "
					+ "WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();
			response.sendRedirect("list");
		} catch (SQLException e) {
			
			e.printStackTrace();
			out.println("系统繁忙，请稍后再试");
		}finally{
		   DBUtils.close(rs, ps, conn);
		}
	}

	
}
