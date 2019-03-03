package base.common;
//”≥…‰¥¶¿Ì∆˜
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;

public class HandlerMapping {
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();
	public void process(List beans) {
		for(Object bean:beans){
			Class clazz = bean.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			System.out.println("clazz:"+clazz);
			System.out.println("method:"+methods);
			for (Method mh : methods) {
				RequestMapping rm = mh.getAnnotation(RequestMapping.class);
				String path = rm.value();
				handlerMap.put(path, new Handler(mh,bean));
				System.out.println("path:"+path);
			}
		}
	}
	public Handler getHandler(String path){
		return  handlerMap.get(path);
	}

}
