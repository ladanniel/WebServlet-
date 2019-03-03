package com.tedu.webserver.servlet;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;
import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

//ClientHandler目前违背了高内聚原则，要进行功能拆分
//负责注册业务
public class RegServlet extends HttpServlet{
	public void service(HttpRequest request ,HttpResponse response){
		/**判断是否请求业务
		 * 
		 *//**注册业务流程
		 * 1：通过request获取用户在注册页面上输入的注册信息
		 * 2：使用RandomAccessFile打开user.dat文件
		 * 3：将giant用户信息写入文件
		 * 4：设置response，响应注册成功页面
		 * 
		 */
		try{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));
		try (RandomAccessFile raf = new RandomAccessFile("user.bat","rw")){
			raf.seek(raf.length());
			byte[]data = username.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = password.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			data = nickname.getBytes();
			data = Arrays.copyOf(data, 32);
			raf.write(data);
			
			raf.writeInt(age);
			
			System.out.println("处理注册业务，并检测异常、、、、、、、、、");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		forward("/myweb/reg_success.html", request,response);
		System.out.println("注册成功，并检测异常、、、、、、、、、、、、、、、、");
	}catch (Exception e) {
		e.printStackTrace();
	}

}
}
