package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
public class TestCase3 {
	



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
			//��÷���ǰ��ע�⣺
				Test test= mh.getAnnotation(Test.class);
				System.out.println("Testע��"+test);
				//ִֻ�д���ע��@Test�ķ���
				if (test!=null) {
					mh.invoke(boj);
					//��ȡ@Testע�������ֵ
					String str = test.value();
					System.out.println("����ע�������ֵ��" + str);
				}
				}
			
		

	}


}
