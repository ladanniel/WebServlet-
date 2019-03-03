package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

public class LoginServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		try {
			String username = request.getParamenters("username");
		    String password = request.getParamenters("password");
		    try (RandomAccessFile raf = 
		    	new	RandomAccessFile("user.dat","r")){
		    	boolean flag = false;
		    	for (int i = 0; i < raf.length()/100; i++) {
					raf.seek(i*100);
					byte[]data = new byte[32];
					raf.read(data);
					String str = new String(data,"UTF-8").trim();
					System.out.println("str---"+str);
					if (str.equals(username)) {
						raf.read(data);
						String pw = new String(data,"UTF-8").trim();
						System.out.println("pw-----"+pw);
						if (pw.equals(password)) {
							flag=true;
							System.out.println("看看程序是否运行到这里、、、、");
							
							forward("/myweb/login_success.html",request,response);
						break;
						}
					}
					if (!flag) {
						forward("/myweb/login_fail.html",request,response);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	

}
