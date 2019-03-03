package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;


/**
 * 映射处理器
 * 负责提供请求路径与处理器的对应关系
 *
 */

public class HandlerMapping {
   //用来存放请求路径与处理器的对应关系
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();
	//依据请求路径，返回Handler对象 (逻辑关系：参见逻辑关系图)
	
	public Handler getHandler(String path){
		return handlerMap.get(path);
	}
	
	
/**
 * beans:处理器实例
 * @param beans
 * 此方法通过遍历beans集合，利用java反射读取@RequestMapping中的路径信息
 * 然后以路径信息作为key,以Handler对象（处理器以及方法的封闭）作为value，添加到Map里面
 */
	
	public void process(List beans) {
		System.out.println("这是Handler的Mapping方法");
		for(Object bean:beans){
			//获得class对象
			Class clazz = bean.getClass();
			//获得所有方法
			Method[] methods = clazz.getDeclaredMethods();
			//遍历所有方法
			for (Method mh:methods) {
				//获得方法前的@RequestMapping
				RequestMapping rm = mh.getAnnotation(RequestMapping.class);
				//获得路径信息(即：请求路径)作为key
				String path = rm.value();
				System.out.println("path:"+path);
				//以请求路径作为key,以handler作为value，将对应关系添加到map里面 增加Handler(封装方法对象实例，处理器)的构造器（mh:方法，bean：处理器）
				handlerMap.put(path, new Handler(mh,bean));
				
			}
		}
		System.out.println("handlerMap:"+handlerMap);
		
	}

}
