package com.tedu.webserver.http;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SAAJResult;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//������HTTP�����Ϣ
public class HttpContext {
	private static Map<Integer,String> STATUS_REASON_MAPPING = new HashMap<Integer,String>();
	private static Map<String,String> MIME_MAPPING = new HashMap<String,String>();
	/**
	 * ���ݸ�����״̬�����ȡ��Ӧ��״̬������ӳ���ϵ
	 * key:״̬����
	 * value��״̬����
	 * ע�����ԭ�򣺸���ۣ������
	 */
	static{
		
		initStatusReasonMapping();
		initMimeMapping();
		
	}
	private static void initStatusReasonMapping(){//��Ӧ״̬
		STATUS_REASON_MAPPING.put(200, "OK");
		STATUS_REASON_MAPPING.put(302, "Move Temporaily");
		STATUS_REASON_MAPPING.put(404, "Not Found");
		STATUS_REASON_MAPPING.put(500, "Internal Server Error");
	}
	//�Ż�httpresponse ����ӦContent-Type��text/html���и��ƣ���ʼ����׺����Content-Type��ӳ��Map
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
	public static String getStatusReason(int statusCode){//��Ӧ״̬����
		return STATUS_REASON_MAPPING.get(statusCode);
	}
    public static String getMime(String ext){
    	return MIME_MAPPING.get(ext);//��粻��ֱ���޸�map,�ṩһ������������Χ�����޸�
    }
	public static void main(String[] args) {
		String reason = getStatusReason(200);
		System.out.println(reason);
		String mine = getMime("jpg");
		System.out.println(mine);
		
	}
	

}
