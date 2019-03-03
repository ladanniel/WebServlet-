package com.tedu.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.tedu.webserver.core.EmptyRequestException;

//负责储存解析内容
public class HttpRequest {
	private Socket socket;
	private InputStream is;
	private String method;
	private String url;
	private String protocol;
	private Map<String,String> headers = new HashMap<String,String>();
	private String requestURI;
	private String queryString;
	private Map<String,String> parameters = new HashMap<String,String>();


	public  HttpRequest(Socket socket) throws EmptyRequestException{
		try {
			this.socket=socket;
			this.is =socket.getInputStream();

			parseRequestLine();
			parseHeaders();
			parseContent();
		}catch(EmptyRequestException e){
			throw e;
		} 
		
		catch (IOException e) {

			e.printStackTrace();
		}


		System.out.print("程序是否到了这里---------------");

	}
	private void parseRequestLine()throws EmptyRequestException{

		/**解析大致流程：
		 * 1：通过输入流读取第一行字符串 2：将请求行按照空格拆分为三项 3：将拆分的三项分别设置到method，url，Protocol三个属性上
		 * 解析请求行时，在获取拆分后的数组元素时，可能会引发数组下标越界，这是由于Http协议允许客户端发送一个空请求来导致的。
		 * 后面解决；
		 */
		String str = readLine();      //调用读取请求行方法并进行拆分
		String arr[]=str.split("\\s");
		if (arr.length<3) {
			throw new EmptyRequestException();
		}
		System.out.println(str.length());

		this.method=arr[0];
		this.url=arr[1];
		parseURL();
		this.protocol=arr[2];
		System.out.println("method--->"+method);
		System.out.println("url-->"+url);
		System.out.println("protocol-->"+protocol);


	}
	/**进一步对url进行解析
	 * 将url中的请求部分设置到属性requestURI上，将url中的参数部分设置到属性queryString上
	 * 在对参数部分进一步解析，将每个参数都存入到属性parameters中
	 * 
	 * 若该url不含有参数部分，则直接将url的值赋值给requestURI，参数部分不做任何处理；
	 * 
	 */
	/**思路：
	 * url是否含有参数，可以根据该url中是否含有？来决定。若有则按照？拆分为两部分
	 * 第一部分为请求部分，第二部分为参数部分，设置到对应属性即可。然后在堆参数进行拆分
	 * 最终将每个参数的名字作为key，值做为value存入到parameter中，
	 * 若不含有参数，则直接将url赋值给requestURI即可
	 *     myweb/reg.html
	 *     myweb/reg?username=fan&........
	 */
	private void parseURL(){
		if (this.url.contains("?")) {
			String []data=url.split("\\?");
			this.requestURI=data[0];
			if (data.length>1) {
				this.queryString=data[1];
				String []arr = queryString.split("&");
				for (String str:arr) {
					String [] strs = str.split("=");
					System.out.println(strs.length);
					if (strs.length>1) {
						this.parameters.put(strs[0], strs[1]);

					}else {
						this.parameters.put(strs[0], null);
					}
				}
			}



		}else {
			this.requestURI=this.url;
		}
		System.out.println("requestURI-->："+requestURI);
		System.out.println("queryString-->："+queryString);

		System.out.println("parameters-->："+parameters);
		System.out.println("url解析完毕！并检测异常，看程序有没有运行到这里、、、、、、、、");
	}

	private void parseHeaders(){
		System.out.println("开始解析消息头、、、、、");
		/**大致步骤：
		 * 1：继续使用readLine方法读取若干行内容，每一行应该都是一个消息头；
		 * 2：当readLine方法返回值为空字符串时则停止循环读取工作（单独读取到CRLF时readLine方法返回值应当
		 * 为空字符串）
		 * 3：每当读取一个消息头信息时，应当按照“：”拆分为两项。第一项为消息头名字，第二行为消息头对应的值，将名字作为key，将
		 * 值作为value存入到属性headers这个map中。
		 */
		String s ="";
		while(true){
			String line = readLine();
			if (s.equals(line)) {
				break;
			}
			String []arr = line.split(":\\s");
			System.out.println(line);

			String key = arr[0];
			String value = arr[1];
			headers.put(arr[0], arr[1]);


		}
		System.out.println("headers--->"+headers);


	}
	private void parseContent(){
		System.out.println("开始解析消息正文、、、、、");

		System.out.println("消息正文解析完毕！");

	}
	private String readLine() {
		//解析请求行

		StringBuilder builder = new StringBuilder();// StringBuffer、StringBuilder字符串生成器（容器：存放的地方）
		int d = -1;
		char c1='a',c2='a';
		try {
			while((d=is.read())!=-1){
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
	public String getParameter(String name) {
		return this.parameters.get(name);
	}
	


}
