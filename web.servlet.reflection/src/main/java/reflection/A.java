package reflection;

public class A {
	public String f1(){
		System.out.println("A1的f1方法");
		return ("I am F1");
	}
	public String f2(){
		System.out.println("A的f2方法");
		return ("I am F2");
		
	}
	public void Foo(String msg){
		System.out.println("A的FOO方法："+msg);
	}

}
