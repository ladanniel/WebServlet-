package base.common;

import java.lang.reflect.Method;

/**
 *Ϊ�˷�������java������ô������ķ�������Ƶ�һ����
 *method.invoke(obj)
 *
 */
public class Handler {
	private Method mh;//����
	private Object obj;//����ʵ��
	
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
