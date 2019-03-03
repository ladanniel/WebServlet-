package com.tedu.webserver.http;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class httpContext {
	private static Map<Integer,String> STATUS_REASON_MAPPING = new HashMap<Integer,String>();
	//处理格式映射关系
	private static Map<String,String> MIME_MAPPING = new HashMap<String,String>();
	//只需要执行一次
	static{
		initStatusReasonMapping();
		initMimeMapping();		
	}
	//初始化状态代码，以后才能使用状态代码
	private static void initStatusReasonMapping(){
		STATUS_REASON_MAPPING.put(200, "OK");
		STATUS_REASON_MAPPING.put(302, "MOVE Temporaily");
		STATUS_REASON_MAPPING.put(404, "NOT FOUND");
		STATUS_REASON_MAPPING.put(500, "Internal Server Error");
		System.out.println("状态执行2");
	}
	 private static void initMimeMapping() {//因为目前支持网页浏览的内容的后缀名很有限，所以采用Tomcat中的web.xml文本
		 try {
			FileInputStream fis = new FileInputStream("/conf/web.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read("fis");
			Element element = doc.getRootElement();
			List<Element> list = element.elements("mime-mapping");
			for(Element listsuffix:list ){
				String key = listsuffix.elementText("extension");
				String value = listsuffix.elementText("mime-type");
				System.out.println("看看集合中的key-valuez键值对"+key+":"+value);
				MIME_MAPPING.put(key, value);
				
			}
			 
		} catch (Exception e) {
			
		}
		 
//		   MIME_MAPPING.put("html" , "text/html" );
//		   MIME_MAPPING.put("png" , "image/png" );
//		   MIME_MAPPING.put("gif" , "image/gif" );
//		   MIME_MAPPING.put("jpg","image/jpeg" );
//		   MIME_MAPPING.put("js","application/javascript" );
				
			}
	//根据给定的状态代码，获取对应描述
	public static String getStatusReason(int statusCode){
		System.out.println("状态执行3");
		return STATUS_REASON_MAPPING.get(statusCode);
		
	}
	
	//用来根据指定的资源后缀获取对应的Content-Type的值
	public static String getMime(String suffix){
		System.out.println("Content-Type格式定义3");
		return MIME_MAPPING.get(suffix);
		
		
	}
	public static void main(String[] args) {
		String reason = getStatusReason(302);
		System.out.println(reason);
		String mime = getMime("js");
		System.out.println(mime);
	}

}
