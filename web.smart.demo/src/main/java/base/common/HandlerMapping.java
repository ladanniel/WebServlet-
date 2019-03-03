package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;

public class HandlerMapping {
    //用于存放请求路径与对应处理器的关系 将整个handler类作为value值存储到map中
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();
	/**
	 * beans:处理器实例（在实例化中将读取到的bean存储到beans集合中，并以集合（作为）的方式传送到HandlerMapping的process方法中）
	 * @param beans
	 * 此方法通过遍历beans集合，利用java反射读取@RequestMapping中的路径信息
	 * 然后以路径信息作为key,以Handler对象（处理器以及方法的封闭）作为value，添加到Map里面
	 */
	public void process(List beans) {
		System.out.println("这是HandlerMapping的方法");
		for(Object bean:beans){
			//反射的前提，要得到被反射的类（也就是要反射到的地方所属的类）
			Class clazz = bean.getClass();
			//反射，两个条件，一，反射到那个类上，二、需要反射那些方法，所以必须要得到所有的方法，根据不同的方法进行反射
			Method [] methods = clazz.getDeclaredMethods();
			//遍历方法，看看方法有哪些
			for (Method mh:methods) {
				//通过注释调用方法，使方法变得简单快捷，复用性更强,所以必须得到注释的类
				RequestMapping rm = mh.getAnnotation(RequestMapping.class);
				//把注释的类(的属性)作为路径，一起传送到前端控制器，供前端控制器调用
				String Path = rm.value();
				handlerMap.put(Path, new Handler(mh,bean));
	           System.out.println("path:"+Path);
			}
		}
	}
		//建立一个get方法把得到的结果返回给前端控制器
			public Handler getHandler(String Path){
				return handlerMap.get(Path);
			}
}
