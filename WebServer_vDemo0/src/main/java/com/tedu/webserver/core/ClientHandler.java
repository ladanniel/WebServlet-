package com.tedu.webserver.core;
import java.io.File;
//负责解析客户端内容
/**处理该客户端的请求的大致步骤
 * 1：解析请求
 * 2：处理请求
 * 3：给与回应
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.Arrays;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;
import com.tedu.webserver.servlet.LoginServlet;
import com.tedu.webserver.servlet.RegServlet;

public class ClientHandler implements Runnable  {
	private Socket socket;
	private File file;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	public void run(){
		try {
			 //处理请求：通过request获取请求的资源路径，从webapps中寻找对应资源
			HttpRequest request = new HttpRequest(socket);//String str接收，换成http存储器接受
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();
			System.out.println("检测异常，是否接收到url"+url);
			
			if ("/myweb/reg".equals(url)) {
				
				RegServlet servlet = new RegServlet();
				servlet.service(request, response);
				System.out.println("检测路径异常-----------》"+url);
			
			}else if("/myweb/login".equals(url)){
//处理登陆业务
					LoginServlet servlet = new LoginServlet(request, response);
					
				}else {
					//处理页面静态资源
					File file = new File("webapps"+url);
					if (file.exists()) {
						response.setStatusCode(200);
						response.setEntity(file);
					}else{
				
				System.out.println("资源未找到、、、、");
				file = new File("webapps/myweb/404.html");
				
				response.setStatusCode(404);
				response.setEntity(file);
				}
			}
			response.flush();
		}catch(EmptyRequestException e){
			System.out.println("空请求！");
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		finally {
			try{
				
			socket.close();
			
			}catch(IOException e){
				e.printStackTrace();
			}
		}

	}
	

}
