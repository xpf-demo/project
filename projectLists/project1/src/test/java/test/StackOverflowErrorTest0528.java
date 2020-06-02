package test;

public class StackOverflowErrorTest0528 {
	
	static int i = 1;
	
	public static void main(String[] args) {
		say();
	}
		
	public static void say() {
		System.out.println("你好"+(++i));
		say();
	}

}
