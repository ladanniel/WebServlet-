package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;


import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

//ClientHandlerĿǰΥ���˸��ھ�ԭ��Ҫ���й��ܲ��
//�����½ҵ��
public class LoginServlet extends HttpServlet{
	public LoginServlet(HttpRequest request,HttpResponse response){
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try (RandomAccessFile raf = new RandomAccessFile("user.bat", "r")){
			boolean flag=false;
			for (int i = 0; i < raf.length()/100; i++) {//����һ���û�����Ϊ100�ֽڣ�����100�����ܹ��洢�˼�������
				raf.seek(i*100);
				byte []data = new byte[32];
				raf.read(data);
				String str = new String(data,"GBK").trim();
				if (str.equals(username)) {
					raf.read(data);
					String pw = new String(data,"GBK").trim();
					if (pw.equals(password)) {
						flag=true;
						System.out.println("��֤�ɹ���������쳣����������������������");
						forward("/myweb/login_success.html", request,response);
					break;
					}
				}
				if (!flag) {
					System.out.println("��½ʧ��");
					forward("/myweb/login_fail.html", request,response);
					
				}
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}

	}

	@Override
	public void service(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	
}
