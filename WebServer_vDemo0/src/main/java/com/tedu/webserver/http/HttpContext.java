package com.tedu.webserver.http;
import java.io.FileInputStream;
//该类定义了HTTP相关信息
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class HttpContext {
	
	 private static Map<Integer,String>STATUS_REASON_MAPPING = new HashMap<Integer,String>();
//	 因为HTTPResponse中发送响应头的Content-Type是写死的发送“text/html”所以定义MIME_MAPPING的方法来处理这个问题	
	 private static Map<String,String>MIME_MAPPING = new HashMap<String,String>();
	 /**资源后缀名与Content-Type之间的映射关系；
	  * key:资源的后缀名
	  * value：该资源对应的Content-Type的值；
	  * 注不同的资源对应的Content-Type的值再W3C上都有定义，可前往W3C官网查询MIME定义
	  * 
	  */
	 static {
			initStatusReasonMapping();
			initMimeMapping();
		}
	//初始化后缀名与Content-Type的映射Map
		private static void initMimeMapping() {
			/**
	   	  * 1:读取conf/web.xml文件，
	   	  * 2:将根元素下所有名为<mime-mapping>的子元素读取出来
	   	  * 3:然后将每个<mime-mapping>元素中的子元素<extension>中间的文本作为key，将子元素<mine-type>
	   	  * 中间的文本作为value，存入到MIME_Mapping中，完成初始化
	   	  * 
	   	  */
			System.out.println("看看程序有没有到这里、、、、、、");
			try {
				FileInputStream in = new FileInputStream("conf/web.xml");
				SAXReader reader = new SAXReader();
				Document doc = reader.read(in);
				
				Element root = doc.getRootElement();
				List<Element> mimeList = root.elements("mime-mapping");
				
				for (Element Ele:mimeList) {
					String key = Ele.elementText("extension");
					String value = Ele.elementText("mime-type");
					MIME_MAPPING.put(key, value);
					
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		根据给定的状态代码获取对应的状态描述；
		private static void initStatusReasonMapping() {
			STATUS_REASON_MAPPING.put(200, "OK");
			STATUS_REASON_MAPPING.put(302, "Move Temporaly");
			STATUS_REASON_MAPPING.put(404, "Not Found");
			STATUS_REASON_MAPPING.put(500, "Internal Server Error");
		}
	//用来根据指定的资源后缀获取对应的Content-Type的值
	//map给ext,有ext在map中修改，体现安全性与封装性
		private static String getMime(String ext) {
			return MIME_MAPPING.get(ext);
			
		}

		
		
	public static String getStatusReason(int statusCode) {
		return STATUS_REASON_MAPPING.get(statusCode);
		
	}
	
	public static void main(String[] args) {
		String reason = getStatusReason(200);
		System.out.println(reason);
	
	}
} 
		 
		 
		
		 
	 
	


  