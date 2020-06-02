package test;

public class OutOfMemoryErrorTest0528 {
	
	public static int count = 1;
	
	public static void main(String[] args) {
		createDog();
		
	}
	
	public static void createDog() {
		while(true) {
			Dog dog = new Dog();
			System.out.println("dog:"+(++count));
		}
	}

}

class Dog{
	
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
