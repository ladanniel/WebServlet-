package com.tedu.webserver.core;
import java.io.File;
//��������ͻ�������
/**����ÿͻ��˵�����Ĵ��²���
 * 1����������
 * 2����������
 * 3�������Ӧ
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
			 //��������ͨ��request��ȡ�������Դ·������webapps��Ѱ�Ҷ�Ӧ��Դ
			HttpRequest request = new HttpRequest(socket);//String str���գ�����http�洢������
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();
			System.out.println("����쳣���Ƿ���յ�url"+url);
			
			if ("/myweb/reg".equals(url)) {
				
				RegServlet servlet = new RegServlet();
				servlet.service(request, response);
				System.out.println("���·���쳣-----------��"+url);
			
			}else if("/myweb/login".equals(url)){
//�����½ҵ��
					LoginServlet servlet = new LoginServlet(request, response);
					
				}else {
					//����ҳ�澲̬��Դ
					File file = new File("webapps"+url);
					if (file.exists()) {
						response.setStatusCode(200);
						response.setEntity(file);
					}else{
				
				System.out.println("��Դδ�ҵ���������");
				file = new File("webapps/myweb/404.html");
				
				response.setStatusCode(404);
				response.setEntity(file);
				}
			}
			response.flush();
		}catch(EmptyRequestException e){
			System.out.println("������");
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
