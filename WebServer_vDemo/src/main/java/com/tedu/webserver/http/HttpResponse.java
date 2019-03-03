package com.tedu.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpResponse {
	
	private Socket socket;
	private OutputStream out;
	private int statusCode;
	/**��Ӧ���������Ϣ����
	 * Ҫ��Ӧ��ʵ���ļ���file��һ����
	 */
	private File entity;
	private Map<String,String> headers = new HashMap<String,String>();
	public HttpResponse(Socket socket) {
		System.out.println("�쳣���0001-------------->");
		try {
			this.socket = socket;
			this.out = socket.getOutputStream();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void flush() {
		
		/**��Ӧ�ͻ�����������
		 * 1������״̬��
		 * 2��������Ӧͷ
		 * 3��������Ӧ����
		 */
		System.out.println("�쳣���00002-------------->");
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	private void sendStatusLine() {
//����״̬�бأ����ϸ���HTTPЭ��ı�׼��ʽ����--�����ݼ���ʽ
		System.out.println("�쳣���00003-------------->");
		try {
		String line = "HTTP/1.1"+" "+statusCode+" "+HttpContext.getStatusReason(statusCode);
		println(line);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//������Ӧͷ�����밴�ձ�׼��ʽ����--�����ݼ���ʽ
	private void sendHeaders() {
		
		System.out.println("�쳣���00004-------------->");
		try {
			Set<Entry<String,String>> set= headers.entrySet();
			for (Entry<String,String> header:set ) {
				String name = header.getKey();
				String value= header.getValue();
				String line = name+": "+value;
				System.out.println(line);
			}
			
//��ʾ��Ӧͷ�������
			println("");
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
//������Ӧ����
	private void sendContent() {
		System.out.println("�쳣���00005-------------->");
		try(FileInputStream fis = new FileInputStream(entity)) {
			byte [] data = new byte[1024*10];
			int len = -1;
			while((len = fis.read(data))!=-1) {
				out.write(data, 0, len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private void println(String line) {
		System.out.println("�쳣���000006-------------->");
		try {
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
			System.out.println(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
	}
	public Map<String, String> putHeaders() {
		return headers;
	}
	

}
