package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
public class TestCase3 {
	



		public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Scanner scan = new Scanner(System.in);
			//读取类名
			String ClassName = scan.nextLine();
			System.out.println("请输入：");
			System.out.println("ClassName:"+ClassName);
			//利用java反射，加载该类，然后实例化
			Class cla = Class.forName(ClassName);
			
			Object boj = cla.newInstance();
			//获得该类的所有方法
			Method[] methods = cla.getDeclaredMethods();
			//遍历所有方法
			for (Method mh:methods) {
			//获得方法前的注解：
				Test test= mh.getAnnotation(Test.class);
				System.out.println("Test注解"+test);
				//只执行带有注解@Test的方法
				if (test!=null) {
					mh.invoke(boj);
					//读取@Test注解的属性值
					String str = test.value();
					System.out.println("这是注解的属性值：" + str);
				}
				}
			
		

	}


}
