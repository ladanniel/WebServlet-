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


//处理（解析）客户端消息
public class ClientHandler implements Runnable{
	private Socket socket;
	
	
	public ClientHandler(Socket socket) {
		this.socket = socket;                                                                                           //私有化提供可使用
	}
	public void run() {//调用解析方法
		
		System.out.println("开始处理解析任务、、、、、、、");
		try {
			HttpRequest request = new HttpRequest(socket);//此处调用request相当于把解析任务交给request
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();
			System.out.println("url-----"+url);
			String servletName = ServerContext.getServletName(url);
			
			System.out.println("servletName"+servletName);
			if (servletName!=null) {//判断是否是注册业务
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
				System.out.println("资源未找到。。。");
				response.setStatusCode(404);
				response.setEntity(new File("webapps/myweb/404.html"));
			}
			
			}
			response.fulsh();
			
		}catch(EmptyRequestException e){
			System.out.println("空请求！");
			
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
