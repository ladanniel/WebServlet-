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
	//�����ʽӳ���ϵ
	private static Map<String,String> MIME_MAPPING = new HashMap<String,String>();
	//ֻ��Ҫִ��һ��
	static{
		initStatusReasonMapping();
		initMimeMapping();		
	}
	//��ʼ��״̬���룬�Ժ����ʹ��״̬����
	private static void initStatusReasonMapping(){
		STATUS_REASON_MAPPING.put(200, "OK");
		STATUS_REASON_MAPPING.put(302, "MOVE Temporaily");
		STATUS_REASON_MAPPING.put(404, "NOT FOUND");
		STATUS_REASON_MAPPING.put(500, "Internal Server Error");
		System.out.println("״ִ̬��2");
	}
	 private static void initMimeMapping() {//��ΪĿǰ֧����ҳ��������ݵĺ�׺�������ޣ����Բ���Tomcat�е�web.xml�ı�
		 try {
			FileInputStream fis = new FileInputStream("/conf/web.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read("fis");
			Element element = doc.getRootElement();
			List<Element> list = element.elements("mime-mapping");
			for(Element listsuffix:list ){
				String key = listsuffix.elementText("extension");
				String value = listsuffix.elementText("mime-type");
				System.out.println("���������е�key-valuez��ֵ��"+key+":"+value);
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
	//���ݸ�����״̬���룬��ȡ��Ӧ����
	public static String getStatusReason(int statusCode){
		System.out.println("״ִ̬��3");
		return STATUS_REASON_MAPPING.get(statusCode);
		
	}
	
	//��������ָ������Դ��׺��ȡ��Ӧ��Content-Type��ֵ
	public static String getMime(String suffix){
		System.out.println("Content-Type��ʽ����3");
		return MIME_MAPPING.get(suffix);
		
		
	}
	public static void main(String[] args) {
		String reason = getStatusReason(302);
		System.out.println(reason);
		String mime = getMime("js");
		System.out.println(mime);
	}

}
