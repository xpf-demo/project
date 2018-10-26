package demo;

import java.util.Optional;

public class OptionalDemo {
	
	public static void main(String[] args) {
		Demo demo = null;
		Optional<Demo> op = Optional.ofNullable(demo);
		System.out.println(op.get());
		System.out.println(op.isPresent());
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
