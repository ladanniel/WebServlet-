package reflection;

public class D {
	@RequestMapping(1)
	public void service(){
		int sum=0;
		for (int i = 0; i <=100; i++) {
			sum =sum+i;
			
		}
		System.out.println("service方法求和sum "+sum);
	}
	@RequestMapping(2)
	public  void set(){
		//99乘法表
		for (int i = 1; i <=9; i++) {
			for (int j = 1; j <=i; j++) {
				System.out.print(j+"*"+i+"="+(i*j)+"\t");
				
			}
			System.out.println();
		}
	}
	@RequestMapping(3)
	public void hello(){
		int arr=10;
		for (int i = 1; i<= arr; i++) {
			for (int j = 0; j <= arr-i; j++) {
				System.out.print("  ");
			}
			for (int j = 0; j <= i-1; j++) {
				System.out.print(" *");
				
			}
			System.out.println();
		}
		for (int j = 0; j <= arr; j++) {
			System.out.print(" *");
			
		}System.out.print("\n");
		for (int i = 1; i<= arr; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("  ");
			}
			for (int j = 0; j <= arr-i; j++) {
				System.out.print(" *");
				
			}
			System.out.println();
		}
	}

}
