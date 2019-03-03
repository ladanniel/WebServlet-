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
	/**响应正文相关信息定义
	 * 要响应的实体文件（file不一样）
	 */
	private File entity;
	private Map<String,String> headers = new HashMap<String,String>();
	public HttpResponse(Socket socket) {
		System.out.println("异常检测0001-------------->");
		try {
			this.socket = socket;
			this.out = socket.getOutputStream();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public void flush() {
		
		/**响应客户端做三件事
		 * 1：发送状态行
		 * 2：发送响应头
		 * 3：发送响应正文
		 */
		System.out.println("异常检测00002-------------->");
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	private void sendStatusLine() {
//发送状态行必，须严格按照HTTP协议的标准格式发送--定内容及格式
		System.out.println("异常检测00003-------------->");
		try {
		String line = "HTTP/1.1"+" "+statusCode+" "+HttpContext.getStatusReason(statusCode);
		println(line);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//发送响应头，必须按照标准格式发送--定内容及格式
	private void sendHeaders() {
		
		System.out.println("异常检测00004-------------->");
		try {
			Set<Entry<String,String>> set= headers.entrySet();
			for (Entry<String,String> header:set ) {
				String name = header.getKey();
				String value= header.getValue();
				String line = name+": "+value;
				System.out.println(line);
			}
			
//表示响应头发送完毕
			println("");
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
//发送响应正文
	private void sendContent() {
		System.out.println("异常检测00005-------------->");
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
		System.out.println("异常检测000006-------------->");
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
