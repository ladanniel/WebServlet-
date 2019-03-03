package com.tedu.webserver.http;
//request处理浏览器请求
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.tedu.webserver.core.EmptyRequestException;

public class httpRequest {
	private Socket socket;
	private InputStream ips;
	//一个标准的request分为：请求方式、路径和协议版本
	private String method;
	private String url;
	private String protocol;
	private Map<String,String> headers = new HashMap<String, String>();
	private String requestURL;
	private String queryString;
	private Map<String,String> parameters = new HashMap<String,String>();

	public httpRequest(Socket socket) throws EmptyRequestException {//因为此处解析可能解析到空字符，为避免异常主动抛出异常
		try {
			this.socket=socket;
			System.out.println("请求1");
			this.ips = socket.getInputStream();
			parseRequestLine();
			parseHeaders();
			parseContent();
		} catch(EmptyRequestException e){
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void parseRequestLine() throws EmptyRequestException {//因为此处解析长度不够，为避免异常主动抛出异常
		String line = readLine();
		System.out.println("请求2");
		String [] arr = line.split("\\s");
		if (arr.length<3) {
			throw new EmptyRequestException();
		}
		this.method = arr[0];
		this.url = arr[1];
		parseURL();
		this.protocol = arr[2];
		System.out.println("方       法:"+method);
		System.out.println("路       径:"+url);
		System.out.println("协议版本:"+protocol);
	}
	private void parseURL() {
		System.out.println("重构：进一步解析url:表单提交时涉及的路径URL以及参数");
		if (this.url.contains("?")) {
			String [] arr = this.url.split("\\?");
			this.requestURL = arr[0];
			if (arr.length>1) {
				this.queryString=arr[1];
				try {//参数转码，优化使用性
					System.out.println("优化操作————转码");
					queryString = URLDecoder.decode(queryString,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			parseParameters(queryString);
			System.out.println("优化queryString，增强复用性、、、、、、、、、、、、、、");
		}else{
			this.requestURL=this.url;
		}
	}
	private void parseParameters(String line) {
		String [] data = line.split("&");
		for (String str:data) {
			String [] doc = str.split("=");
			if (doc.length>1) {
				this.parameters.put(doc[0], doc[1]);
			}else{
				this.parameters.put(doc[0], "");
			}
		}
	}
	private void parseHeaders() {

		System.out.println("执行3");
		while (true) {
			String line = readLine();
			if (line.equals("")) {
				break;
			}
			String [] str = line.split(":\\s");
			headers.put(str[0], str[1]);
			System.out.println("选择post表单方式，消息头要放在正文中：：：line:"+line);
		}
		
	}
	private void parseContent() {
		System.out.println("重构正文：：如果选择以post形式提交表单，则必须放在正文中，对解析正文进行重构");
		if (this.headers.containsKey("Content-Length")) {
			int length = Integer.parseInt(headers.get("Content-Length"));
			String type = this.headers.get("Content-Type");
			if ("application/x-www-form-urlencoded".equals(type)) {
				//还原headers内容到正文里
				byte [] data = new byte[length];
				try {
					ips.read(data);//还原为字符串
					String line = new String(data,"ISO8859-1");
					//再转码
					line = URLDecoder.decode(line,"UTF-8");
					parseParameters(line);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}
	}
	private String readLine() {
      System.out.println("多线程情况下，StringBuilder并不安全，而采用StringBuffer,内部自带syncronize锁");
		StringBuffer builder = new StringBuffer();
		System.out.println("解析请求  =="+builder);
		int lenght = -1;
		char c1='a',c2='a';
		try {
			while((lenght=ips.read())!=-1){
				c2=(char) lenght;
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
	public String getRequestURL() {
		return requestURL;
	}
	public String getQueryString() {
		return queryString;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public String getParameters(String name) {
		return parameters.get(name);
	}

}
