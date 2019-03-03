package com.tedu.webserver.servlet;

import java.io.File;

import com.tedu.webserver.http.HttpRequest;
import com.tedu.webserver.http.HttpResponse;

/**所有Servlet的超类，规定了Servlet的功能
 * 
 * @author live
 *
 */

public abstract class HttpServlet {
	public abstract void service(HttpRequest request,HttpResponse response);
		/**跳转到指定路径
		 * 在tomcat中实际上是定义在转发器上的功能。
		 * tomcat以链式的结构将各组件之间串联在一起，进行跳转调用
		 * 
		 */
	public void forward(String url,HttpRequest resquest,HttpResponse response){
		response.setStatusCode(200);
		response.setEntity(new File("webapps"+url));
		
	}
	

}
 




