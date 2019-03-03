package com.tedu.webserver.http;
import java.io.IOException;
//�������
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
	private String requestURI;//���Ƿ��漰ע��ҵ��Ϳ��ͻ��������·��
	private String queryString;
	private Map<String,String> headers = new HashMap<String, String>();//����
    private Map<String,String> paramenters = new HashMap<String,String>();
	public HttpRequest(Socket socket) throws EmptyRequestException {

		try {
			this.socket=socket;
			this.in=socket.getInputStream();//�õ��׽��ֲ����浽in ���ڴ��͵�readline�н��н���
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
		System.out.println("��ʼ���������С���������");
		String line = readLine();
		String[]arr = line.split("\\s");
//		System.out.println("arr������ĳ���"+arr.length);
		if (arr.length<3) {
			throw new EmptyRequestException();
		}
		this.method=arr[0];
		this.url=arr[1];
		//��һ������url
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
		System.out.println("����쳣-------------");
	}
	
	
	
	
	
	public void RequestHeaders(){
		System.out.println("��ʼ������Ϣͷ��������");
		while(true){
			String line = readLine();
//			System.out.println(line);
			if ("".equals(line)) {
				break;
			}
			String []str = line.split(":\\s");	
			
		this.headers.put(str[0], str[1]);
		}
		System.out.println("��Ϣͷ������ϡ�������");

	}

	public void RequestContent(){
		
		if (headers.containsKey("Content-Length")) {
			System.out.println("��ʼ������Ϣ���ġ���������");
			int length = Integer.parseInt(headers.get("Content-Length"));
			String type = headers.get("Content-Type");
			System.out.println( "type=="+type);
			System.out.println("��Ϣ���ĵĳ���"+length);
		}
	}
	public String readLine(){//�ṩר�Ž����ͻ��˽��յ�����Ϣ  //�˴�������Ҳ��ʹ��StringBufferһ���ɱ���ַ����С�//�����ṩһ���� StringBuffer ���ݵ� API��������֤ͬ����//���౻������� StringBuffer ��һ�������滻�������ַ����������������߳�ʹ�õ�ʱ������������ձ飩��  //������ܣ��������Ȳ��ø��࣬��Ϊ�ڴ����ʵ���У����� StringBuffer Ҫ�졣
		StringBuilder builder = new StringBuilder();                                                                    
		int length =-1;
		char c1='a',c2='a';                                 //���CTRL
		try {
			while((length=in.read())!=-1){
				c2=(char)length;                            //c2�õ��ַ�����Ҫ���ַ������ж��Ƿ����
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
