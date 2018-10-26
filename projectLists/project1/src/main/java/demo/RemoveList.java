package demo;

import java.util.ArrayList;
import java.util.List;

public class RemoveList {
	
	
	
	public static void main(String[] args) {
		Person p1 = new Person("1", 1);
		Person p2 = new Person("2", 2);
		Person p3 = new Person("3", 3);
		List<Person> persons = new ArrayList<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		p2.setName("10");
		List<Person> persons1 = new ArrayList<>();
		persons1.add(p1);
		persons1.add(p2);
		
		System.out.println(persons);
		System.out.println(persons1);
		System.out.println("-------before--------");
		System.out.println(persons.removeAll(persons1));
		System.out.println("-------after--------");
		System.out.println(persons);
		System.out.println(persons1);
		
		
		
	}

}

class Person{
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
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
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}
