package demo;

public class PassByValueDemo {
	
	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.setName("小黑");
		cat.setAge(10);
		System.out.println("[对象]before change:"+cat);
		changeCat(cat);
		System.out.println("[对象]after change:"+cat);
		
		int a = 10;
		int b = 20;
		System.out.println("[基础数据类型]before change: a:"+a+" ,b:"+b);
		changeNumber(a,b);
		System.out.println("[基础数据类型]after change: a:"+a+" ,b:"+b);
		
		String[] array = {"a","b"};
		System.out.println("[数组]before change array:"+array[0]);
		changeArray(array);
		System.out.println("[数组]after change array:"+array[0]);
		
		String str = "a";
		System.out.println("[String] before change :"+str);
		changeString(str);
		System.out.println("[String] after change:" +str);
		
		
	}
	
	public static void changeCat(Cat cat) {
		cat.setAge(20);
	}
	
	public static void changeNumber(int a,int b) {
		a = 20;
		b = 10;
	}
	
	public static void changeArray(String[] array) {
		array[0] = "aaa";
	}
	
	public static void changeString(String str) {
		str = "aaa";
	}

}


final class Cat{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}
	
}
