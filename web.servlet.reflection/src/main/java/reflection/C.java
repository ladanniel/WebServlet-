package reflection;
//开发注解
public class C {
	@Test(value="娃哈哈")
	public void m1(){
		System.out.println("C的方法m1");
	}
    @Test(value="呵呵")
	public String m2(){
		System.out.println("C的方法m2");
		return "i am m2";
	}
	public void foo(){
		System.out.println("C的方法foo");
	}
}
