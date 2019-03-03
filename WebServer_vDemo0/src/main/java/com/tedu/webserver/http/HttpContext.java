package com.tedu.webserver.http;
import java.io.FileInputStream;
//���ඨ����HTTP�����Ϣ
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class HttpContext {
	
	 private static Map<Integer,String>STATUS_REASON_MAPPING = new HashMap<Integer,String>();
//	 ��ΪHTTPResponse�з�����Ӧͷ��Content-Type��д���ķ��͡�text/html�����Զ���MIME_MAPPING�ķ����������������	
	 private static Map<String,String>MIME_MAPPING = new HashMap<String,String>();
	 /**��Դ��׺����Content-Type֮���ӳ���ϵ��
	  * key:��Դ�ĺ�׺��
	  * value������Դ��Ӧ��Content-Type��ֵ��
	  * ע��ͬ����Դ��Ӧ��Content-Type��ֵ��W3C�϶��ж��壬��ǰ��W3C������ѯMIME����
	  * 
	  */
	 static {
			initStatusReasonMapping();
			initMimeMapping();
		}
	//��ʼ����׺����Content-Type��ӳ��Map
		private static void initMimeMapping() {
			/**
	   	  * 1:��ȡconf/web.xml�ļ���
	   	  * 2:����Ԫ����������Ϊ<mime-mapping>����Ԫ�ض�ȡ����
	   	  * 3:Ȼ��ÿ��<mime-mapping>Ԫ���е���Ԫ��<extension>�м���ı���Ϊkey������Ԫ��<mine-type>
	   	  * �м���ı���Ϊvalue�����뵽MIME_Mapping�У���ɳ�ʼ��
	   	  * 
	   	  */
			System.out.println("����������û�е��������������");
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
//		���ݸ�����״̬�����ȡ��Ӧ��״̬������
		private static void initStatusReasonMapping() {
			STATUS_REASON_MAPPING.put(200, "OK");
			STATUS_REASON_MAPPING.put(302, "Move Temporaly");
			STATUS_REASON_MAPPING.put(404, "Not Found");
			STATUS_REASON_MAPPING.put(500, "Internal Server Error");
		}
	//��������ָ������Դ��׺��ȡ��Ӧ��Content-Type��ֵ
	//map��ext,��ext��map���޸ģ����ְ�ȫ�����װ��
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
		 
		 
		
		 
	 
	


  