package com.tedu.webserver.core;


import java.io.File;
import java.io.IOException;
import java.net.Socket;
import com.tedu.webserver.http.httpRequest;
import com.tedu.webserver.http.httpResponse;
import com.tedu.webserver.servlet.LoginServlet;
import com.tedu.webserver.servlet.RegServlet;
import com.tedu.webserver.servlet.httpServlet;

public class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket = socket;

	}
	public void run() {

		try {
			httpRequest request = new httpRequest(socket);
			httpResponse response = new httpResponse(socket);
			String url = request.getRequestURL();
			String servletName = ServerContext.getServletName(url);//���÷����Ż���Ŀ
			if (servletName!=null) {
				Class cls = Class.forName(servletName);//��·�����з���
				System.out.println("���÷����Ż���Ŀ������"+cls+":"+servletName);
				httpServlet servlet = (httpServlet) cls.newInstance();
				servlet.Service(request, response);	
			}else{

				File file = new File("webapps"+url);
				System.out.println("·����⣺��"+file);
				if (file.exists()) {
					response.setStatusCode(200);
					response.setEntity(file);
					System.out.println("��Դ�Ѿ��ҵ�");
				}else {
					response.setStatusCode(404);
					response.setEntity(new File("webapps/myweb/404.html"));
					System.out.println("��Դδ�ҵ�");
				}
			}
			response.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}




}
