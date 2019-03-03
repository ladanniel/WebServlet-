package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.annotation.RequestMapping;

public class HandlerMapping {
    //���ڴ������·�����Ӧ�������Ĺ�ϵ ������handler����Ϊvalueֵ�洢��map��
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();
	/**
	 * beans:������ʵ������ʵ�����н���ȡ����bean�洢��beans�����У����Լ��ϣ���Ϊ���ķ�ʽ���͵�HandlerMapping��process�����У�
	 * @param beans
	 * �˷���ͨ������beans���ϣ�����java�����ȡ@RequestMapping�е�·����Ϣ
	 * Ȼ����·����Ϣ��Ϊkey,��Handler���󣨴������Լ������ķ�գ���Ϊvalue����ӵ�Map����
	 */
	public void process(List beans) {
		System.out.println("����HandlerMapping�ķ���");
		for(Object bean:beans){
			//�����ǰ�ᣬҪ�õ���������ࣨҲ����Ҫ���䵽�ĵط��������ࣩ
			Class clazz = bean.getClass();
			//���䣬����������һ�����䵽�Ǹ����ϣ�������Ҫ������Щ���������Ա���Ҫ�õ����еķ��������ݲ�ͬ�ķ������з���
			Method [] methods = clazz.getDeclaredMethods();
			//����������������������Щ
			for (Method mh:methods) {
				//ͨ��ע�͵��÷�����ʹ������ü򵥿�ݣ������Ը�ǿ,���Ա���õ�ע�͵���
				RequestMapping rm = mh.getAnnotation(RequestMapping.class);
				//��ע�͵���(������)��Ϊ·����һ���͵�ǰ�˿���������ǰ�˿���������
				String Path = rm.value();
				handlerMap.put(Path, new Handler(mh,bean));
	           System.out.println("path:"+Path);
			}
		}
	}
		//����һ��get�����ѵõ��Ľ�����ظ�ǰ�˿�����
			public Handler getHandler(String Path){
				return handlerMap.get(Path);
			}
}
