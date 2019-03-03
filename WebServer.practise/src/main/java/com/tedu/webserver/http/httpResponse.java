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

public class httpResponse {
	private Socket socket;
	private OutputStream ops;
	private int statusCode;
	private File Entity;
    private Map<String,String> headers = new HashMap<String,String>();
	public httpResponse(Socket socket) {
		
		try {
			this.socket = socket;
			this.ops = socket.getOutputStream();
			System.out.println("��Ӧ1");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void flush(){
		sendStatusLine();
		sendHeaders();
		sendContent();

	}

	private void sendStatusLine() {
		System.out.println("��Ӧ2");
		String line = "HTTP/1.1"+" "+statusCode+" "+httpContext.getStatusReason(statusCode);
		println(line);
		System.out.println("���bug  =="+line);
	}
	//�ض���
	

	public void sendRedirect(String url){
		this.statusCode=302;
		this.headers.put("location", url);
		
	}

	private void sendHeaders() {
		System.out.println("��Ӧ3");
		System.out.println("������ֵط�");

		try {
			Set<Entry<String,String>> set = headers.entrySet();
			System.out.println("����set�е�����"+set);
			for(Entry<String,String> header:set){
				String name = header.getKey();
				String value = header.getValue();
				String line = name+": "+value;
				println(line);
				
			}
			ops.write(13);
			ops.write(10);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	private void sendContent() {
		System.out.println("��ȡʵ��");
      try (FileInputStream file = new FileInputStream(Entity)){//��ȡʵ���ļ���response
    	 
    	  byte [] data = new byte[1024*10];
    	  int length =-1;
    	  while((length = file.read(data))!=-1){
    		  ops.write(data, 0, length);  
    	  }
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
	private void println(String line){
		System.out.println("��ȡ��Ӧ��׼�ַ���");
		try {
			
			ops.write(line.getBytes("ISO8859-1"));
			ops.write(13);
			ops.write(10);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void putHeaders(String name,String value){
		this.headers = headers;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public File getEntity() {
		return Entity;
	}
	//�ع��ı���ͼƬ�����ʽ����Щ��ʽ������ʵ��entity����
	public void setEntity(File Entity) {
		this.Entity = Entity;
		/**1�������ӦͷContent-Length
		 * 
		 */
		this.headers.put("Content-Length", Entity.length()+"");
		/**�����ӦͷContent-Type
		 * 1����ͨ��entity��ȡ���ļ������֣�
		 * 2����ȡ���ļ����ĺ�׺��
		 * 3��ͨ��HTTPContext���ݸú�׺����ȡ����Ӧ��Content-Type��ֵ��
		 * 4����headers�����ø���Ӧͷ��Ϣ��
		 */
		//2.1 ���磺xxx.png,��ȡ��Ӧ�ļ���
		String name = Entity.getName();
		//2.2 png
		String ext = name.substring(name.lastIndexOf(".")+1);
		//2.3
		String type  = httpContext.getMime(ext);
		this.headers.put("Content-Type", type);
		
		
	}
	



}
