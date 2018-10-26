package demo;

public class ExtendsAndSuperDemo {
	
	public static void main(String[] args) {
		Man man = new Man();
		Balck<Man> b = new Balck<>(man);
		b.bSay();
		
	}
	
	
}

class Man{
	public void say() {
		System.out.println("hello world！");
	}
}


class Balck<T extends Man>{
	private T t;
	public Balck() {}
	public Balck(T t){
		this.t = t;
	}
	public void  bSay() {
		t.say();
	} 
}
