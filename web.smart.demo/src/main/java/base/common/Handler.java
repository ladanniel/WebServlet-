package base.common;
/**
 *Ϊ�˷�������java������ô������ķ�������Ƶ�һ����
 *method.invoke(obj)
 *
 */
import java.lang.reflect.Method;

public class Handler {
	private Method mh;
	private Object obj;

	public Handler(Method mh, Object obj) {
		System.out.println("����Handler�ķ���");
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
