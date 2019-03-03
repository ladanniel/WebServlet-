package com.tedu.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	/**������Servletӳ���ϵ
	 * key:����·��
	 * value����ӦServlet������
	 * 
	 */
	private static Map<String,String>servletMapping =new  HashMap<>();
	 static{
		initServletMapping();
	}
	
	
	
	public static void initServletMapping(){  // ��ʼ��������Servletӳ���ϵ
		/**��ȡconf/server.xml�ļ���������<servlet>��ǩ���������������е�url����ֵ��Ϊkey��
		 * className����ֵ��Ϊvalue���뵽servletMapping��
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
	public static String getServletName(String url){//���������ȥ��Ӧ��Servlet����
       
		return servletMapping.get(url);
		
		
		
	}
	public static void main(String[] args) {
		
		String servlet = getServletName("/myweb/reg");
		System.out.println(servlet);
		
	}
	
}
