package com.tedu.webserver.http;

import java.util.HashMap;
import java.util.Map;

//���ඨ����HTTP�����Ϣ
/**
 * ���ݸ�����״̬�����ȡ��Ӧ��״̬������ӳ���ϵ
 * key:״̬����
 * value��״̬����
 * ע�����ԭ�򣺸���ۣ������
 */
public class HttpContext {
	private static Map<Integer,String>STATUS_REASON_MAPPING = new HashMap<>();
	static{
		iniStatusReasonMapping();
		
	}
	private static void iniStatusReasonMapping(){
		STATUS_REASON_MAPPING.put(200, "OK");
		STATUS_REASON_MAPPING.put(302, "Move Temporaily");
		STATUS_REASON_MAPPING.put(404, "Not Found");
		STATUS_REASON_MAPPING.put(500, "Internal Server Error");
	}
//	 ���ݸ�����״̬�����ȡ��Ӧ��״̬������
	public static String getStatusReason(int statusCode){
		return STATUS_REASON_MAPPING.get(statusCode);
		
	}
	public static void main(String[] args) {
		String reason = getStatusReason(302);
		System.out.println(reason);
	}

}
