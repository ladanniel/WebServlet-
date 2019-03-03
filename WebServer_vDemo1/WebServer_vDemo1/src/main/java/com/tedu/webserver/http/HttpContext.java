package com.tedu.webserver.http;

import java.util.HashMap;
import java.util.Map;

//该类定义了HTTP相关信息
/**
 * 根据给定的状态代码获取对应的状态描述的映射关系
 * key:状态代码
 * value：状态描述
 * 注：设计原则：高类聚，低耦合
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
//	 根据给定的状态代码获取对应的状态描述；
	public static String getStatusReason(int statusCode){
		return STATUS_REASON_MAPPING.get(statusCode);
		
	}
	public static void main(String[] args) {
		String reason = getStatusReason(302);
		System.out.println(reason);
	}

}
