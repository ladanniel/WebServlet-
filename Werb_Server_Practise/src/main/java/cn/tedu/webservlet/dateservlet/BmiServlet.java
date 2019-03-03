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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		double weight = 120;
		double height = 80;
		double bmi = weight/height/height;

		  if (bmi<19) {
			out.println("您的体重过轻"+bmi);
		}else{
		    out.println("您的体重过重"+bmi);
		}
		  
		  out.close();
		
	}
	
		
	

	

	

	
	

}
