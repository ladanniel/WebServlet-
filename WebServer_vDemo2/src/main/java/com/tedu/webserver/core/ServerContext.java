package com.tedu.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	/**请求与Servlet映射关系
	 * key:请求路径
	 * value：对应Servlet的名字
	 * 
	 */
	private static Map<String,String>servletMapping =new  HashMap<>();
	 static{
		initServletMapping();
	}
	
	
	
	public static void initServletMapping(){  // 初始化请求与Servlet映射关系
		/**读取conf/server.xml文件，将所有<servlet>标签解析出来，用其中的url属性值作为key，
		 * className属性值作为value存入到servletMapping中
		 * 
		 */try{
			    SAXReader reader = new SAXReader();
			    Document doc =reader.read(new File("conf/server.xml")); 
			    Element root= doc.getRootElement();
			    
			    Element servlets = root.element("servlets");
			    
			    List<Element>servletList=servlets.elements();
			    
			    for (Element ServletEle:servletList) {
			    	String key =ServletEle.attributeValue("url");
			    	System.out.println("key"+key);
			    	String value = ServletEle.attributeValue("className");
			    	System.out.println("value"+value);
			    	servletMapping.put(key, value);
		    
		    }
		    }catch(Exception e){
		    	e.printStackTrace();
		    	
		    }
		    
	
//		servletMapping.put("/myweb/reg", "com.tedu.webserver.servlet.RegServlet");
//		servletMapping.put("/myweb/login", "com.tedu.webserver.servlet.LoginServerlet");
		
		
	}
	public static String getServletName(String url){//根据请求回去对应的Servlet名字
       
		return servletMapping.get(url);
		
		
		
	}
	public static void main(String[] args) {
		
		String servlet = getServletName("/myweb/reg");
		System.out.println(servlet);
		
	}
	
}
