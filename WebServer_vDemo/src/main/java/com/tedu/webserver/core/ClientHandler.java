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

			/**判断是否请求业务
	         * 
	         */
			if ("/myweb/reg".equals(url)) {
				/**注册业务流程
				 * 1：通过request获取用户在注册页面上输入的注册信息
				 * 2：使用RandomAccessFile打开user.dat文件
				 * 3：将giant用户信息写入文件
				 * 4：设置response，响应注册成功页面
				 * 
				 */
				System.out.println("检测异常------------------------!!!!!!");
				String username = request.getParameters("username");
				String password = request.getParameters("password");
				String nickname = request.getParameters("nickname");
				int age = Integer.parseInt(request.getParameters("age"));
				
				try(RandomAccessFile raf = new RandomAccessFile("user.dat","rw");) {
					raf.seek(raf.length());
					byte [] data = username.getBytes();
					data=Arrays.copyOf(data, 32);
					raf.write(data);
					
					data = password.getBytes();
					data=Arrays.copyOf(data, 32);
					raf.write(data);
					
					data = nickname.getBytes();
					data=Arrays.copyOf(data, 32);
					raf.write(data);
					
					raf.writeInt(age);
					;
					System.out.println("注册完毕");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.setStatusCode(200);
				File file = new File("webapps/myweb/reg_success.html");
				response.setEntity(file);
				System.out.println("资源已经找到、、、、、");
			}else {
				System.out.println("资源未找到、、、、");
				file = new File("webapps/myweb/404.html");
				
				response.setStatusCode(404);
				response.setEntity(file);
			}
			response.flush();
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
