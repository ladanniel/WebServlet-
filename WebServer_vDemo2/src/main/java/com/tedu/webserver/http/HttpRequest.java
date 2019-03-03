package com.tedu.webserver.http;
import java.io.IOException;
//储存对象
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.tedu.webserver.core.EmptyRequestException;

public class HttpRequest {
	private String method;
	private String url;
	private String protocol;
	private Socket socket;
	private InputStream in;
	private String requestURI;//看是否涉及注册业务就看客户端请求的路径
	private String queryString;
	private Map<String,String> headers = new HashMap<String, String>();//储存
    private Map<String,String> paramenters = new HashMap<String,String>();
	public HttpRequest(Socket socket) throws EmptyRequestException {

		try {
			this.socket=socket;
			this.in=socket.getInputStream();//得到套接字并储存到in 里在传送到readline中进行解析
			RequestLine();
			RequestHeaders();
			RequestContent();
			
		}catch(EmptyRequestException e){
			throw e;
		}catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	public void RequestLine() throws EmptyRequestException{
		System.out.println("开始解析请求行、、、、、");
		String line = readLine();
		String[]arr = line.split("\\s");
//		System.out.println("arr解析后的长度"+arr.length);
		if (arr.length<3) {
			throw new EmptyRequestException();
		}
		this.method=arr[0];
		this.url=arr[1];
		//进一步解析url
		parseURL();
		this.protocol=arr[2];
		System.out.println("method=="+method);
		System.out.println("url=="+url);
		System.out.println("protocol=="+protocol);
	}
	public void parseURL(){
		if (this.url.contains("?")) {
			String []str=this.url.split("\\?");
			this.requestURI=str[0];
			if (str.length>1) {
		    this.queryString=str[1];
			}
			String[]arr=queryString.split("&");
			for (String arrs:arr) {
				String []strs = arrs.split("=");
				if (strs.length>1) {
					paramenters.put(strs[0], strs[1]);
				}else{
					paramenters.put(strs[0], null);
				}
			}
			
		}else{
		this.requestURI=this.url;
		}
		System.out.println("requestURI:"+requestURI);
		System.out.println("queryString:"+queryString);
		System.out.println("paramenters:"+paramenters);
		System.out.println("检查异常-------------");
	}
	
	
	
	
	
	public void RequestHeaders(){
		System.out.println("开始解析消息头、、、、");
		while(true){
			String line = readLine();
//			System.out.println(line);
			if ("".equals(line)) {
				break;
			}
			String []str = line.split(":\\s");	
			
		this.headers.put(str[0], str[1]);
		}
		System.out.println("消息头解析完毕、、、、");

	}

	public void RequestContent(){
		
		if (headers.containsKey("Content-Length")) {
			System.out.println("开始解析消息正文、、、、、");
			int length = Integer.parseInt(headers.get("Content-Length"));
			String type = headers.get("Content-Type");
			System.out.println( "type=="+type);
			System.out.println("消息正文的长度"+length);
		}
	}
	public String readLine(){//提供专门解析客户端接收到的信息  //此处理论上也可使用StringBuffer一个可变的字符序列。//此类提供一个与 StringBuffer 兼容的 API，但不保证同步。//该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。  //如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。
		StringBuilder builder = new StringBuilder();                                                                    
		int length =-1;
		char c1='a',c2='a';                                 //解决CTRL
		try {
			while((length=in.read())!=-1){
				c2=(char)length;                            //c2得到字符，需要对字符进行判定是否读完
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
	public String getRequestURI() {
		return requestURI;
	}
	public String getQueryString() {
		return queryString;
	}
	public String  getParamenters(String name) {
		return paramenters.get(name);
	}

  

}
