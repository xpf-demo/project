package demo;

public class RuntimeDemo {
	
	public static void main(String[] args) {
		/*Runtime runtime = Runtime.getRuntime();
		System.out.println("MAX_MEMORY:"+runtime.maxMemory()/1024/1024);
		System.out.println("TOTAL_MEMORY:"+runtime.totalMemory()/1024/1024);*/
//		System.out.println(member.getClass().getClassLoader());
//		System.out.println(member.getClass().getClassLoader().getParent());
//		System.out.println(member.getClass().getClassLoader().getParent().getParent());
		
		
		String str = "";
		for(int i = 0; i < 10; i++) {
			str += i;
			System.out.println(str);
			str.intern();
		}
		
	}

}


class Member{}