package com.tedu.webserver.servlet;

import java.io.File;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

/**����Servlet�ĳ��࣬�涨��Servlet�Ĺ���
 * 
 * @author live
 *
 */

public abstract class HttpServlet {
	public abstract void service(HttpRequest request,HttpResponse response);
		/**��ת��ָ��·��
		 * ��tomcat��ʵ�����Ƕ�����ת�����ϵĹ��ܡ�
		 * tomcat����ʽ�Ľṹ�������֮�䴮����һ�𣬽�����ת����
		 * 
		 */
	public void forward(String url,HttpRequest resquest,HttpResponse response){
		response.setStatusCode(200);
		response.setEntity(new File("webapps"+url));
		
	}
	

}
 




