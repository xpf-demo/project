package demo;

import java.util.Optional;

public class OptionalDemo {
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		Optional<Demo> op = Optional.ofNullable(demo);
		System.out.println(op.isPresent());
		System.out.println(op.get().getName());
	}

}

class Demo{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
