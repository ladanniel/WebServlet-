package reflection;
//����ע��
public class C {
	@Test(value="�޹���")
	public void m1(){
		System.out.println("C�ķ���m1");
	}
    @Test(value="�Ǻ�")
	public String m2(){
		System.out.println("C�ķ���m2");
		return "i am m2";
	}
	public void foo(){
		System.out.println("C�ķ���foo");
	}
}
