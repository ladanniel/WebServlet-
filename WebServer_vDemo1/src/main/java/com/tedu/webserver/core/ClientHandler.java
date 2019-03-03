package com.tedu.webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket socket;
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	public void run(){
		System.out.println("启动一个线程处理客户端连接、、、、");
	}
	public String readLine(InputStream in){
		StringBuilder builder = new StringBuilder();
		char c1='a',c2='a';
		int len = -1;
		try {
			while((len = in.read())!=-1){
				c2=(char) len;
				if (c1==13&&c2==10) {
					break;
				}
				builder.append(c2);
				c1=c2;
				
			}
			return builder.toString().trim();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "";
	}

}
