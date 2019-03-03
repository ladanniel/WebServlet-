package com.tedu.webserver.servlet;
import java.io.File;
import com.tedu.webserver.http.httpRequest;
import com.tedu.webserver.http.httpResponse;
public abstract class httpServlet {
	public abstract void Service(httpRequest request,httpResponse response);
	public void forward(String url,httpRequest request,httpResponse response){
		response.setStatusCode(200);
		response.setEntity(new File("webapps"+url));
	}

}
