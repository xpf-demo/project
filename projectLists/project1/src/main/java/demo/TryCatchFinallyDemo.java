package demo;

public class TryCatchFinallyDemo {
	
	public static void main(String[] args) {
		//String[] str = {"demo","one"};
		try {
			//String a = str[2];
			System.out.println(0);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(1);
			return;
		}finally {
			System.out.println(3);
		}
	}

}
