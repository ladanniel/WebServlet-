package cn.tedu.webservlet.dateservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BmiServlet extends HttpServlet {

	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String weight =request.getParameter("weight");
		String height = request.getParameter("height");
		/**
		 * 一般意义上：（此处）在服务端，一定要对请求参数值进行合法性检查。
		 */
		Double we = Double.parseDouble(weight);
		Double he = Double.parseDouble(height);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		double bmi = we/he/he*100;
          String status = "正常";
		  if (bmi<19) {
			status="您的身体体重过轻";
		}else{
			status="您的身体体重过重";
		}
		  
		  String []interest = request.getParameterValues("interest");
		  if (interest!=null) {
			  for (String str:interest) {
				System.out.println(str);
			}
			
		}
		  
		  out.println("您的bmi指数为："+bmi+"；体重："+status);
		  /**
		   * out.close方法可以不调用，容器最后会自动关闭
		   */
		  out.close();
		
	}
	
		
	

	
	

	

	
	

}
