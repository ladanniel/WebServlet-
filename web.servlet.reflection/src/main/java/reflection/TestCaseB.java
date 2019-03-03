package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestCaseB {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner scan = new Scanner(System.in);
		System.out.println("«Î ‰»Î£∫");
		String className = scan.nextLine();
		Class cla = Class.forName(className);
		Object obj = cla.newInstance();
		Method[] me = cla.getDeclaredMethods();
		Object ob = null;
		for(Method met:me){
			String name = met.getName();
			
			if (name.startsWith("test")) {
				ob = met.invoke(obj);
			}
		}
	}

}
