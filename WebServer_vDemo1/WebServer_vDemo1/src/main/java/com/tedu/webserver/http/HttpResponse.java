package com.tedu.webserver.http;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
/**��Ӧ����
 * �����ÿ��ʵ�����ڱ�ʾһ������˷��͸��ͻ��˵���Ӧ����
 * ע�����͸��ͻ��˵�����
 * ע�����ԭ�򣺸���ۣ�����ϣ����ܲ�Ĳ����ٲ�ÿ������ֻ����Ӧ�ø������һ���֣�
 */
public class HttpResponse {
	private Socket socket; //�ͻ��˽ӿ�
	private OutputStream out;
	private int statusCode;//����״̬
	private File entity;//�ļ�ʵ��
	
	public HttpResponse(Socket socket){
		/**��Ӧ���������Ϣ����
		 * Ҫ��Ӧ��ʵ���ļ���file��һ����
		 */
		 
		 try {
			 this.socket=socket;
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		
		
		
	}
	//����Ӧ������http��׼��ʽ���͸��ͻ���
	public void flush(){
		/**��Ӧ�ͻ�����������
		 * 1������״̬��
		 * 2��������Ӧͷ
		 * 3��������Ӧ����
		 */
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	public void sendStatusLine(){
		
		
	}
	public void sendHeaders(){
		
	}
	public void sendContent(){
		
	}
	

}
