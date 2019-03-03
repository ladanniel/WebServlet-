package com.tedu.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.event.TreeWillExpandListener;

public class WebServer {
	private ServerSocket server;
	public WebServer(){
		try {
			server = new ServerSocket(8090);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public void start(){

		try {
			System.out.println("等待客户端连接、、、、");
			Socket socket = server.accept();
			System.out.println("一个客户端已经连接、、、、");
			ClientHandler handler = new ClientHandler(socket);
			Thread t = new Thread(handler);
			t.start();
			InputStream in = socket.getInputStream();
			int d= -1;
			while ((d=in.read())!=-1) {
				System.out.print((char)d);
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}

}
