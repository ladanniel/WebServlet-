package base.common;
/**
 *为了方便利用java反射调用处理器的方法而设计的一个类
 *method.invoke(obj)
 *
 */
import java.lang.reflect.Method;

public class Handler {
	private Method mh;
	private Object obj;

	public Handler(Method mh, Object obj) {
		System.out.println("这是Handler的方法");
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
