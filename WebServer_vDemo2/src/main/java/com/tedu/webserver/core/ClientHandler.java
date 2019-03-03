package com.tedu.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;
import com.tedu.webserver.servlet.HttpServlet;
import com.tedu.webserver.servlet.LoginServlet;
import com.tedu.webserver.servlet.RegServlet;


//�����������ͻ�����Ϣ
public class ClientHandler implements Runnable{
	private Socket socket;
	
	
	public ClientHandler(Socket socket) {
		this.socket = socket;                                                                                           //˽�л��ṩ��ʹ��
	}
	public void run() {//���ý�������
		
		System.out.println("��ʼ����������񡢡�����������");
		try {
			HttpRequest request = new HttpRequest(socket);//�˴�����request�൱�ڰѽ������񽻸�request
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();
			System.out.println("url-----"+url);
			String servletName = ServerContext.getServletName(url);
			
			System.out.println("servletName"+servletName);
			if (servletName!=null) {//�ж��Ƿ���ע��ҵ��
//			RegServlet servlet= new RegServlet();
//			servlet.service(request, response);
			
				Class cls = Class.forName(servletName);
				System.out.println("url--"+url+"."+"servletName"+servletName);
				HttpServlet servlet = (HttpServlet) cls.newInstance();
					servlet.service(request, response);
					
					
			}else{
			
			File file = new File("webapps"+url);
			if (file.exists()) {
				response.setStatusCode(200);
				response.setEntity(file);
			}else{
				System.out.println("��Դδ�ҵ�������");
				response.setStatusCode(404);
				response.setEntity(new File("webapps/myweb/404.html"));
			}
			
			}
			response.fulsh();
			
		}catch(EmptyRequestException e){
			System.out.println("������");
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	

	
	
	
	

}
