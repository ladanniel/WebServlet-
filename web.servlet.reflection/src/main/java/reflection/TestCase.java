package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestCase {
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
			String mName = mh.getName();
			System.err.println("mName:"+mName);
			Object rv=null;
			if ("FOO".equals(mName)) {
				//调用带参数的方法
				Object [] num = new Object[]{"balabala"};
				rv = mh.invoke(boj, num);
				
			}
			  rv = mh.invoke(boj);
			System.out.println("反射方法的返回值："+rv);
		}
	}

}
