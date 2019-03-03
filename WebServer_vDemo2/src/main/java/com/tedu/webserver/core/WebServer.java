package com.tedu.webserver.core;
//设计一个简单的底层服务端
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	
	public WebServer(){//开启服务端端口
		try {
			server = new ServerSocket(8090);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void start(){//开启传输服务套接字
		while(true){
		try {
			System.out.println("等待客户端连接、、、、、、、");
			Socket socket = server.accept();
			System.out.println("一个客户端已经连接！");
			ClientHandler handler=new ClientHandler(socket);   //创建接收对象，需要调用线程开启进程
			Thread t= new Thread(handler);
			t.start();
                  				
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		}
	}
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}

}
