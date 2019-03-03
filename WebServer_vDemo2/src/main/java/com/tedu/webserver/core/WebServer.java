package com.tedu.webserver.core;
//���һ���򵥵ĵײ�����
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket server;
	
	public WebServer(){//��������˶˿�
		try {
			server = new ServerSocket(8090);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void start(){//������������׽���
		while(true){
		try {
			System.out.println("�ȴ��ͻ������ӡ�������������");
			Socket socket = server.accept();
			System.out.println("һ���ͻ����Ѿ����ӣ�");
			ClientHandler handler=new ClientHandler(socket);   //�������ն�����Ҫ�����߳̿�������
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
