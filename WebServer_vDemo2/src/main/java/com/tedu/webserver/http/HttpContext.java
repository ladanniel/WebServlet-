package com.tedu.webserver.http;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SAAJResult;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//定义了HTTP相关信息
public class HttpContext {
	private static Map<Integer,String> STATUS_REASON_MAPPING = new HashMap<Integer,String>();
	private static Map<String,String> MIME_MAPPING = new HashMap<String,String>();
	/**
	 * 根据给定的状态代码获取对应的状态描述的映射关系
	 * key:状态代码
	 * value：状态描述
	 * 注：设计原则：高类聚，低耦合
	 */
	static{
		
		initStatusReasonMapping();
		initMimeMapping();
		
	}
	private static void initStatusReasonMapping(){//响应状态
		STATUS_REASON_MAPPING.put(200, "OK");
		STATUS_REASON_MAPPING.put(302, "Move Temporaily");
		STATUS_REASON_MAPPING.put(404, "Not Found");
		STATUS_REASON_MAPPING.put(500, "Internal Server Error");
	}
	//优化httpresponse 中响应Content-Type中text/html进行改善，初始化后缀名与Content-Type的映射Map
	public static void initMimeMapping(){
		
		try {
			FileInputStream fis = new FileInputStream("conf/web.xml");
			
			SAXReader reader = new SAXReader();
			
			Document doc= reader.read(fis);
			
			Element root = doc.getRootElement();
			
			List<Element> list = root.elements("mime-mapping");
			for(Element ele:list){
				String key = ele.elementText("extension");
				String value=ele.elementText("mime-type");
				MIME_MAPPING.put(key, value);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
//		   MIME_MAPPING.put("html" , "text/html" );
//		   MIME_MAPPING.put("png" , "image/png" );
//		   MIME_MAPPING.put("gif" , "image/gif" );
//		   MIME_MAPPING.put("jpg","image/jpeg" );
//		   MIME_MAPPING.put("js","application/javascript" );
//		
	}
	public static String getStatusReason(int statusCode){//回应状态描述
		return STATUS_REASON_MAPPING.get(statusCode);
	}
    public static String getMime(String ext){
    	return MIME_MAPPING.get(ext);//外界不能直接修改map,提供一个参数，在外围进行修改
    }
	public static void main(String[] args) {
		String reason = getStatusReason(200);
		System.out.println(reason);
		String mine = getMime("jpg");
		System.out.println(mine);
		
	}
	

}
