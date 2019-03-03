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

//������Ӧ�ͻ��˵����󣬲��������͸��ͻ���//����ClientHandler
public class HttpResponse {
	private Socket socket;
	private OutputStream out;
	private int statusCode;
	private File entity;
	private Map<String,String> headers = new HashMap<String,String>();
	public HttpResponse(Socket socket){//�ӿ�

		try {
			this.socket=socket;
			this.out=socket.getOutputStream();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void fulsh(){//��Ӧ�ͻ��˵ķ����ṩ����
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	public void sendStatusLine(){//״̬���������ֹ��ɣ�״̬�У�״̬�����״̬�������������
		String line = "HTTP/1.1"+" "+statusCode+" "+HttpContext.getStatusReason(statusCode);
		println(line);
	}
	public void sendHeaders(){//��Ӧ���� ��Content-Type��Content-Length����//����text/html
        try{
		Set<Entry<String,String>> set = headers.entrySet();
		for(Entry<String,String> header:set){
			String name=header.getKey();
			String value=header.getValue();
			String line =name+": "+value; 
			println(line);
	}
		 println("");
        }catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendContent(){
		try (FileInputStream fis = new FileInputStream(entity)){
			byte []data = new byte[1024*10];
			int length=-1;
			while((length=fis.read(data))!=-1){
				out.write(data, 0, length);

			}			
		} catch (Exception e) {		
		}	
	}
	public void println(String line){//ר���ṩһ����������дCRLF
		try {
			out.write(line.getBytes("ISO8859-1"));
			out.write(13);
			out.write(10);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void putHeaders(String name,String value){
		this.headers=headers;
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
		/**������Ӧ��ʵ���ļ����ݣ��÷������Զ���Ӷ�Ӧ��������ӦͷContent-Type��Content-Length
		entity���ṩ���������޸�map�е�ֵ
		 */
		this.headers.put("Content-Length", entity.length()+"");
		String name = entity.getName();
		String ext = name.substring(name.lastIndexOf(".")+1);
		String type=HttpContext.getMime(ext);
		this.headers.put("Content-Type",type);
		
		
	}
	
	


}
