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
			System.out.println("响应1");
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
		System.out.println("响应2");
		String line = "HTTP/1.1"+" "+statusCode+" "+httpContext.getStatusReason(statusCode);
		println(line);
		System.out.println("检测bug  =="+line);
	}
	//重定向
	

	public void sendRedirect(String url){
		this.statusCode=302;
		this.headers.put("location", url);
		
	}

	private void sendHeaders() {
		System.out.println("响应3");
		System.out.println("问题出现地方");

		try {
			Set<Entry<String,String>> set = headers.entrySet();
			System.out.println("看看set中的内容"+set);
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
		System.out.println("读取实体");
      try (FileInputStream file = new FileInputStream(Entity)){//读取实体文件在response
    	 
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
		System.out.println("获取响应标准字符集");
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
	//重构文本、图片传输格式，这些格式依赖于实体entity传送
	public void setEntity(File Entity) {
		this.Entity = Entity;
		/**1：添加响应头Content-Length
		 * 
		 */
		this.headers.put("Content-Length", Entity.length()+"");
		/**添加响应头Content-Type
		 * 1：先通过entity获取该文件的名字；
		 * 2：获取该文件名的后缀；
		 * 3：通过HTTPContext根据该后缀名获取到对应的Content-Type的值；
		 * 4：向headers中设置该响应头信息；
		 */
		//2.1 例如：xxx.png,获取对应文件名
		String name = Entity.getName();
		//2.2 png
		String ext = name.substring(name.lastIndexOf(".")+1);
		//2.3
		String type  = httpContext.getMime(ext);
		this.headers.put("Content-Type", type);
		
		
	}
	



}
