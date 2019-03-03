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
		 * һ�������ϣ����˴����ڷ���ˣ�һ��Ҫ���������ֵ���кϷ��Լ�顣
		 */
		Double we = Double.parseDouble(weight);
		Double he = Double.parseDouble(height);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		double bmi = we/he/he*100;
          String status = "����";
		  if (bmi<19) {
			status="�����������ع���";
		}else{
			status="�����������ع���";
		}
		  
		  String []interest = request.getParameterValues("interest");
		  if (interest!=null) {
			  for (String str:interest) {
				System.out.println(str);
			}
			
		}
		  
		  out.println("����bmiָ��Ϊ��"+bmi+"�����أ�"+status);
		  /**
		   * out.close�������Բ����ã����������Զ��ر�
		   */
		  out.close();
		
	}
	
		
	

	
	

	

	
	

}
