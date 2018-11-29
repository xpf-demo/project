package demo;

public class Test {
	
	public static void main(String[] args) {
		
	}
	
	
	
	interface Bluetooth{
		void btFunction();
	}
	
	
	abstract class Phone{
		protected String name;
		public Phone() {}
		public Phone(String name) {
			this.name = name;
		}
		
//		public abstract void a() {}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}

}
