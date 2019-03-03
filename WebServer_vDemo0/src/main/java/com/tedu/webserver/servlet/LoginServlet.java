package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;


import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

//ClientHandler目前违背了高内聚原则，要进行功能拆分
//负责登陆业务
public class LoginServlet extends HttpServlet{
	public LoginServlet(HttpRequest request,HttpResponse response){
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try (RandomAccessFile raf = new RandomAccessFile("user.bat", "r")){
			boolean flag=false;
			for (int i = 0; i < raf.length()/100; i++) {//看看一个用户数据为100字节，除以100，看总共存储了几个数据
				raf.seek(i*100);
				byte []data = new byte[32];
				raf.read(data);
				String str = new String(data,"GBK").trim();
				if (str.equals(username)) {
					raf.read(data);
					String pw = new String(data,"GBK").trim();
					if (pw.equals(password)) {
						flag=true;
						System.out.println("验证成功，并检测异常、、、、、、、、、、、");
						forward("/myweb/login_success.html", request,response);
					break;
					}
				}
				if (!flag) {
					System.out.println("登陆失败");
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
