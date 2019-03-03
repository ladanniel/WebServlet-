package com.tedu.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.tedu.webserver.core.EmptyRequestException;

//���𴢴��������
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


		System.out.print("�����Ƿ�������---------------");

	}
	private void parseRequestLine()throws EmptyRequestException{

		/**�����������̣�
		 * 1��ͨ����������ȡ��һ���ַ��� 2���������а��տո���Ϊ���� 3������ֵ�����ֱ����õ�method��url��Protocol����������
		 * ����������ʱ���ڻ�ȡ��ֺ������Ԫ��ʱ�����ܻ����������±�Խ�磬��������HttpЭ������ͻ��˷���һ�������������µġ�
		 * ��������
		 */
		String str = readLine();      //���ö�ȡ�����з��������в��
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
	/**��һ����url���н���
	 * ��url�е����󲿷����õ�����requestURI�ϣ���url�еĲ����������õ�����queryString��
	 * �ڶԲ������ֽ�һ����������ÿ�����������뵽����parameters��
	 * 
	 * ����url�����в������֣���ֱ�ӽ�url��ֵ��ֵ��requestURI���������ֲ����κδ���
	 * 
	 */
	/**˼·��
	 * url�Ƿ��в��������Ը��ݸ�url���Ƿ��У����������������գ����Ϊ������
	 * ��һ����Ϊ���󲿷֣��ڶ�����Ϊ�������֣����õ���Ӧ���Լ��ɡ�Ȼ���ڶѲ������в��
	 * ���ս�ÿ��������������Ϊkey��ֵ��Ϊvalue���뵽parameter�У�
	 * �������в�������ֱ�ӽ�url��ֵ��requestURI����
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
		System.out.println("requestURI-->��"+requestURI);
		System.out.println("queryString-->��"+queryString);

		System.out.println("parameters-->��"+parameters);
		System.out.println("url������ϣ�������쳣����������û�����е������������������");
	}

	private void parseHeaders(){
		System.out.println("��ʼ������Ϣͷ����������");
		/**���²��裺
		 * 1������ʹ��readLine������ȡ���������ݣ�ÿһ��Ӧ�ö���һ����Ϣͷ��
		 * 2����readLine��������ֵΪ���ַ���ʱ��ֹͣѭ����ȡ������������ȡ��CRLFʱreadLine��������ֵӦ��
		 * Ϊ���ַ�����
		 * 3��ÿ����ȡһ����Ϣͷ��Ϣʱ��Ӧ�����ա��������Ϊ�����һ��Ϊ��Ϣͷ���֣��ڶ���Ϊ��Ϣͷ��Ӧ��ֵ����������Ϊkey����
		 * ֵ��Ϊvalue���뵽����headers���map�С�
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
		System.out.println("��ʼ������Ϣ���ġ���������");

		System.out.println("��Ϣ���Ľ�����ϣ�");

	}
	private String readLine() {
		//����������

		StringBuilder builder = new StringBuilder();// StringBuffer��StringBuilder�ַ�������������������ŵĵط���
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
