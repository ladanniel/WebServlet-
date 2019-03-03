package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

import com.tedu.webserver.http.httpRequest;
import com.tedu.webserver.http.httpResponse;

public class RegServlet extends httpServlet{
	public void Service(httpRequest request, httpResponse response){
		System.out.println("重构：注册业务、、、、、、、");
		try {
			String username = request.getParameters("username");
			String password = request.getParameters("password");
			String nickname = request.getParameters("nickname");
			int age = Integer.parseInt(request.getParameters("age"));
			try (RandomAccessFile raf = new RandomAccessFile("user.dat", "rw")){
				raf.seek(raf.length());
				byte[]data = username.getBytes("UTF-8");
				data=Arrays.copyOf(data, 32);
				raf.write(data);

				data = password.getBytes("UTF-8");
				data = Arrays.copyOf(data, 32);
				raf.write(data);

				data = nickname.getBytes("UTF-8");
				data = Arrays.copyOf(data, 32);
				raf.write(data);

				raf.writeInt(age);

			} catch (Exception e) {
				e.printStackTrace();
			}
			forward("/myweb/reg_success.html", request, response);
			response.sendRedirect("reg_success.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
