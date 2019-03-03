package cn.tedu.webservlet.dateservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DateServlet extends HttpServlet{
 
	public DateServlet() {
		System.out.println("日期输出");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		out.println(str);
		out.close();
			
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter num = response.getWriter();
//		out.print("<h1>你的高考成绩是"+num+"<h1>:500");
//		num.close();
		
	 
	}
	
	
     
}
