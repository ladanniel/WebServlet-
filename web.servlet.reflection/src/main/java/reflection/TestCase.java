package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestCase {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner scan = new Scanner(System.in);
		//��ȡ����
		String ClassName = scan.nextLine();
		System.out.println("�����룺");
		System.out.println("ClassName:"+ClassName);
		//����java���䣬���ظ��࣬Ȼ��ʵ����
		Class cla = Class.forName(ClassName);
		
		Object boj = cla.newInstance();
		//��ø�������з���
		Method[] methods = cla.getDeclaredMethods();
		//�������з���
		for (Method mh:methods) {
			String mName = mh.getName();
			System.err.println("mName:"+mName);
			Object rv=null;
			if ("FOO".equals(mName)) {
				//���ô������ķ���
				Object [] num = new Object[]{"balabala"};
				rv = mh.invoke(boj, num);
				
			}
			  rv = mh.invoke(boj);
			System.out.println("���䷽���ķ���ֵ��"+rv);
		}
	}

}
