package base.common;

import java.lang.reflect.Method;

/**
 *为了方便利用java反射调用处理器的方法而设计的一个类
 *method.invoke(obj)
 *
 */
public class Handler {
	private Method mh;//方法
	private Object obj;//对象实例
	
	public Handler(Method mh, Object obj) {
		this.mh = mh;
		this.obj = obj;
	}
	public Method getMh() {
		return mh;
	}
	public void setMh(Method mh) {
		this.mh = mh;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	

}
