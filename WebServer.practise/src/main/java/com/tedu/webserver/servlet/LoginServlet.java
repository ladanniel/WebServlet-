package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;

import com.tedu.webserver.http.httpRequest;
import com.tedu.webserver.http.httpResponse;

public class LoginServlet extends httpServlet {
	public void Service(httpRequest request,httpResponse response){
		System.out.println("重构登录业务、、、、、、、");
		try {
			String username = request.getParameters("username");
			String password = request.getParameters("password");
			try (RandomAccessFile raf = new RandomAccessFile("user.dat","r")){
				for (int i = 0; i < raf.length()/100; i++) {
					raf.seek(i*100);
					boolean flag = false;
					byte[] data = new byte[32];
					raf.read(data);
					String name = new String(data,"UTF-8").trim();
					if (username.equals(name)) {
						raf.read(data);
						String pw = new String(data,"UTF-8").trim();
						if (password.equals(pw)) {
							flag=true;
							forward("/myweb/login_success.html", request, response);
							break;
						}
					}
					if (!flag) {
						forward("/myweb/login_fail.html", request, response);
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
