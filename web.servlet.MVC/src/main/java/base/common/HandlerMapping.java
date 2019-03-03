package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;


/**
 * ӳ�䴦����
 * �����ṩ����·���봦�����Ķ�Ӧ��ϵ
 *
 */

public class HandlerMapping {
   //�����������·���봦�����Ķ�Ӧ��ϵ
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();
	//��������·��������Handler���� (�߼���ϵ���μ��߼���ϵͼ)
	
	public Handler getHandler(String path){
		return handlerMap.get(path);
	}
	
	
/**
 * beans:������ʵ��
 * @param beans
 * �˷���ͨ������beans���ϣ�����java�����ȡ@RequestMapping�е�·����Ϣ
 * Ȼ����·����Ϣ��Ϊkey,��Handler���󣨴������Լ������ķ�գ���Ϊvalue����ӵ�Map����
 */
	
	public void process(List beans) {
		System.out.println("����Handler��Mapping����");
		for(Object bean:beans){
			//���class����
			Class clazz = bean.getClass();
			//������з���
			Method[] methods = clazz.getDeclaredMethods();
			//�������з���
			for (Method mh:methods) {
				//��÷���ǰ��@RequestMapping
				RequestMapping rm = mh.getAnnotation(RequestMapping.class);
				//���·����Ϣ(��������·��)��Ϊkey
				String path = rm.value();
				System.out.println("path:"+path);
				//������·����Ϊkey,��handler��Ϊvalue������Ӧ��ϵ��ӵ�map���� ����Handler(��װ��������ʵ����������)�Ĺ�������mh:������bean����������
				handlerMap.put(path, new Handler(mh,bean));
				
			}
		}
		System.out.println("handlerMap:"+handlerMap);
		
	}

}
