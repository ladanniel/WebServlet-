package com.tedu.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	private static Map<String,String> servletMapping = new HashMap<String,String>();
	static{
		initServletMapping();
	}
	private static void initServletMapping(){
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("conf/server.xml"));
			Element root = doc.getRootElement();
			Element ele = (Element) root.element("servlets");
			List<Element> list = ele.elements();
			for(Element eles:list){
				String key = eles.attributeValue("url");
				String value = eles.attributeValue("className");
				servletMapping.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getServletName(String url){
		return servletMapping.get(url);
		
	}
	public static void main(String[] args) {
		String servletName = getServletName("/myweb/login");
		System.out.println(servletName);
	}
}
