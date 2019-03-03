package com.tedu.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private InputStream in;
	private Socket socket;
	private String method;
	private String url;
	private String protocol;
    private Map<String,String>headers = new HashMap<>();

	public HttpRequest(Socket socket) {
		
		
		try {
			this.socket = socket;
			this.in = socket.getInputStream();
			parseRequestLine();
			parseHeader();
			parseContent();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public void parseRequestLine(){
		System.out.println("开始解析请求行、、、、");
	
		     String list = readLine();
		     System.out.println("检测异常-------"+list);
		     String []arr = list.split("\\s");
		     this.method = arr[0];
		     this.url = arr[1];
		     this.protocol = arr[2];
		     System.out.println("method--->"+method+"url--->"+url+"protocol--->"+protocol);
		
	}
	public void parseHeader(){
		System.out.println("开始解析请求路径、、、、、");
		
		while(true){
			String line = readLine();
			if ("".equals(line)) {
				break;
				
			}
			String [] arrs = line.split(":");
			this.headers.put(arrs[0], arrs[1]);
		}
		System.out.println("消息正文解析完毕！");
	}
	public void parseContent(){
		System.out.println("开始解析消息头、、、、、");
		
	}
	//定义一个读取字符串的方法流程
	public String readLine(){
		StringBuilder builder = new StringBuilder();
		int d=-1;
		char c1='a',c2='a';
		//读取字符串到CRLF时候停止
		try {
			while ((d=in.read())!=-1) {
				c2=(char)d;
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
	public InputStream getIn() {
		return in;
	}
	public Socket getSocket() {
		return socket;
	}
	public String getMethod() {
		return method;
	}
	public String getUrl() {
		return url;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getHeaders(String name) {
		return headers.get(name);
	}
	

}
