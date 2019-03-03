package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

public class RegServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		
		try {
			String username = request.getParamenters("username");
			String password = request.getParamenters("password");
			String nickname = request.getParamenters("nickname");
			int age = Integer.parseInt(request.getParamenters("age"));
			try (RandomAccessFile raf = 
					new RandomAccessFile("user.dat","rw")){
				raf.seek(raf.length());
				byte[]data = username.getBytes();
				data=Arrays.copyOf(data, 32);
				raf.write(data);
				System.out.println("看看程序有没有运行到这里。。。。。。。");
				data=password.getBytes();
				data=Arrays.copyOf(data, 32);
				raf.write(data);
				
				data=nickname.getBytes();
				data=Arrays.copyOf(data, 32);
				raf.write(data);
				
				raf.writeInt(age);
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		forward("/myweb/reg_success.html",request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	

}
