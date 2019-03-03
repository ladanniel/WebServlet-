package com.tedu.webserver.core;
import java.io.File;
//��������ͻ�������
/**����ÿͻ��˵�����Ĵ��²���
 * 1����������
 * 2����������
 * 3�������Ӧ
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
			 //��������ͨ��request��ȡ�������Դ·������webapps��Ѱ�Ҷ�Ӧ��Դ
			HttpRequest request = new HttpRequest(socket);//String str���գ�����http�洢������
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();

			/**�ж��Ƿ�����ҵ��
	         * 
	         */
			if ("/myweb/reg".equals(url)) {
				/**ע��ҵ������
				 * 1��ͨ��request��ȡ�û���ע��ҳ���������ע����Ϣ
				 * 2��ʹ��RandomAccessFile��user.dat�ļ�
				 * 3����giant�û���Ϣд���ļ�
				 * 4������response����Ӧע��ɹ�ҳ��
				 * 
				 */
				System.out.println("����쳣------------------------!!!!!!");
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
					System.out.println("ע�����");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.setStatusCode(200);
				File file = new File("webapps/myweb/reg_success.html");
				response.setEntity(file);
				System.out.println("��Դ�Ѿ��ҵ�����������");
			}else {
				System.out.println("��Դδ�ҵ���������");
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
