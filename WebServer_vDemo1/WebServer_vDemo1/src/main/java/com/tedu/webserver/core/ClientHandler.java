package com.tedu.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.tedu.webserver.http.HttpRequest;


public class ClientHandler implements Runnable {
	 private Socket socket;

	public ClientHandler(Socket socket) {
		
		this.socket = socket;
	}
	public void run(){
		/**处理该客户端的请求的大致步骤
		 * 1：解析请求
		 * 2：处理请求
		 * 3：给与回应
		 * 
		 */
		
		try {
			//读取客户端发送过来的消息
			String line = readLine(socket.getInputStream());
			System.out.println("看看有没有读到这里"+line);
			HttpRequest request = new HttpRequest(socket);
			//处理请求：通过request获取请求的资源路径，从webapps中寻找对应资源
			String url = request.getUrl();
			File file = new File("webapps"+url);
			if (file.exists()) {
				file = new File("webapps/myweb/index.html");
				//以一个标准的Http响应格式回复客户端该资源	
				OutputStream os = socket.getOutputStream();
				//发送状态行
				String list = "HTTP/1.1 200 OK";
				os.write(list.getBytes("ISO8859-1"));
				os.write(13);
				os.write(10);
				System.out.println("程序走到这里了");
				 //发送响应头
				list = "Content-Type: text/html";
				os.write(list.getBytes("ISO8859-1"));
				os.write(13);
				os.write(10);
				System.out.println("程序走到这里了");
				os.write(13);
				os.write(10);
				
				//发送响应正文
				FileInputStream fis = new FileInputStream(file);
				byte[]data = new byte[1024*10];
				int d = -1;
				while ((fis.read(data))!=-1) {
					os.write(data, 0, d);

				}
			}else {
				System.out.println("资源未找到");
					 file = new File("webapps/myweb/404.html");
					 OutputStream os = socket.getOutputStream();
					 String str = "HTTP/1.1 404 NOT FOUND";
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 System.out.println("程序运行到这里");
					 
					 str = "Content-Type: text/404.html";
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 
					 str = "Content-Length: "+file.length();
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 
					 os.write(13);
					 os.write(10);
					 
				 FileInputStream fis = new FileInputStream(file);
				 byte[]data = new byte[1024*10];
				 int d = -1;
				 while ((d=fis.read(data))!=-1) {
					os.write(data,0,d);
					
				}
					 
				}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public String readLine(InputStream in){
		StringBuilder bulider = new StringBuilder();
		int d = -1;
		char c1='a',c2='a';
		try {
			while ((d=in.read())!=-1) {
				c2=(char)d;
				if (c1==13&&c2==10) {
					break;
				}
				bulider.append(c2);
				c1=c2;
				
			}
			return bulider.toString().trim();
		} catch (Exception e) {
			
		}
		return "";
		
	}

}
