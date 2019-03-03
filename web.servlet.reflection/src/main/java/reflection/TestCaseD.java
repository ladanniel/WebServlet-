package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class TestCaseD {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner scan = new Scanner(System.in);
		System.out.println("«Î ‰»Î£∫");
		String className = scan.nextLine();
		Class cla = Class.forName(className);
		Object name = cla.newInstance();
		Method [] me = cla.getDeclaredMethods();
		for(Method met : me){
			String  str = met.getName();
			RequestMapping rm = met.getAnnotation(RequestMapping.class);
			if (rm!=null) {
				met.invoke(name);
				System.out.println("str:"+str);
			}
		}
		
		
	}
	
	
	
	

}
