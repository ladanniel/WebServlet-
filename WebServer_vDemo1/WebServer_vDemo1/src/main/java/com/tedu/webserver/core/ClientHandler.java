package com.tedu.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.tedu.webserver.http.HttpRequest;


public class ClientHandler implements Runnable {
	 private Socket socket;

	public ClientHandler(Socket socket) {
		
		this.socket = socket;
	}
	public void run(){
		/**����ÿͻ��˵�����Ĵ��²���
		 * 1����������
		 * 2����������
		 * 3�������Ӧ
		 * 
		 */
		
		try {
			//��ȡ�ͻ��˷��͹�������Ϣ
			String line = readLine(socket.getInputStream());
			System.out.println("������û�ж�������"+line);
			HttpRequest request = new HttpRequest(socket);
			//��������ͨ��request��ȡ�������Դ·������webapps��Ѱ�Ҷ�Ӧ��Դ
			String url = request.getUrl();
			File file = new File("webapps"+url);
			if (file.exists()) {
				file = new File("webapps/myweb/index.html");
				//��һ����׼��Http��Ӧ��ʽ�ظ��ͻ��˸���Դ	
				OutputStream os = socket.getOutputStream();
				//����״̬��
				String list = "HTTP/1.1 200 OK";
				os.write(list.getBytes("ISO8859-1"));
				os.write(13);
				os.write(10);
				System.out.println("�����ߵ�������");
				 //������Ӧͷ
				list = "Content-Type: text/html";
				os.write(list.getBytes("ISO8859-1"));
				os.write(13);
				os.write(10);
				System.out.println("�����ߵ�������");
				os.write(13);
				os.write(10);
				
				//������Ӧ����
				FileInputStream fis = new FileInputStream(file);
				byte[]data = new byte[1024*10];
				int d = -1;
				while ((fis.read(data))!=-1) {
					os.write(data, 0, d);

				}
			}else {
				System.out.println("��Դδ�ҵ�");
					 file = new File("webapps/myweb/404.html");
					 OutputStream os = socket.getOutputStream();
					 String str = "HTTP/1.1 404 NOT FOUND";
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 System.out.println("�������е�����");
					 
					 str = "Content-Type: text/404.html";
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 
					 str = "Content-Length: "+file.length();
					 os.write(str.getBytes("ISO8859-1"));
					 os.write(13);
					 os.write(10);
					 
					 os.write(13);
					 os.write(10);
					 
				 FileInputStream fis = new FileInputStream(file);
				 byte[]data = new byte[1024*10];
				 int d = -1;
				 while ((d=fis.read(data))!=-1) {
					os.write(data,0,d);
					
				}
					 
				}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public String readLine(InputStream in){
		StringBuilder bulider = new StringBuilder();
		int d = -1;
		char c1='a',c2='a';
		try {
			while ((d=in.read())!=-1) {
				c2=(char)d;
				if (c1==13&&c2==10) {
					break;
				}
				bulider.append(c2);
				c1=c2;
				
			}
			return bulider.toString().trim();
		} catch (Exception e) {
			
		}
		return "";
		
	}

}
