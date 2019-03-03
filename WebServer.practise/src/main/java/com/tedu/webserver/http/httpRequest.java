package com.tedu.webserver.http;
//request�������������
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
	//һ����׼��request��Ϊ������ʽ��·����Э��汾
	private String method;
	private String url;
	private String protocol;
	private Map<String,String> headers = new HashMap<String, String>();
	private String requestURL;
	private String queryString;
	private Map<String,String> parameters = new HashMap<String,String>();

	public httpRequest(Socket socket) throws EmptyRequestException {//��Ϊ�˴��������ܽ��������ַ���Ϊ�����쳣�����׳��쳣
		try {
			this.socket=socket;
			System.out.println("����1");
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
	private void parseRequestLine() throws EmptyRequestException {//��Ϊ�˴��������Ȳ�����Ϊ�����쳣�����׳��쳣
		String line = readLine();
		System.out.println("����2");
		String [] arr = line.split("\\s");
		if (arr.length<3) {
			throw new EmptyRequestException();
		}
		this.method = arr[0];
		this.url = arr[1];
		parseURL();
		this.protocol = arr[2];
		System.out.println("��       ��:"+method);
		System.out.println("·       ��:"+url);
		System.out.println("Э��汾:"+protocol);
	}
	private void parseURL() {
		System.out.println("�ع�����һ������url:���ύʱ�漰��·��URL�Լ�����");
		if (this.url.contains("?")) {
			String [] arr = this.url.split("\\?");
			this.requestURL = arr[0];
			if (arr.length>1) {
				this.queryString=arr[1];
				try {//����ת�룬�Ż�ʹ����
					System.out.println("�Ż�������������ת��");
					queryString = URLDecoder.decode(queryString,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			parseParameters(queryString);
			System.out.println("�Ż�queryString����ǿ�����ԡ���������������������������");
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

		System.out.println("ִ��3");
		while (true) {
			String line = readLine();
			if (line.equals("")) {
				break;
			}
			String [] str = line.split(":\\s");
			headers.put(str[0], str[1]);
			System.out.println("ѡ��post����ʽ����ϢͷҪ���������У�����line:"+line);
		}
		
	}
	private void parseContent() {
		System.out.println("�ع����ģ������ѡ����post��ʽ�ύ�����������������У��Խ������Ľ����ع�");
		if (this.headers.containsKey("Content-Length")) {
			int length = Integer.parseInt(headers.get("Content-Length"));
			String type = this.headers.get("Content-Type");
			if ("application/x-www-form-urlencoded".equals(type)) {
				//��ԭheaders���ݵ�������
				byte [] data = new byte[length];
				try {
					ips.read(data);//��ԭΪ�ַ���
					String line = new String(data,"ISO8859-1");
					//��ת��
					line = URLDecoder.decode(line,"UTF-8");
					parseParameters(line);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}
	}
	private String readLine() {
      System.out.println("���߳�����£�StringBuilder������ȫ��������StringBuffer,�ڲ��Դ�syncronize��");
		StringBuffer builder = new StringBuffer();
		System.out.println("��������  =="+builder);
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
