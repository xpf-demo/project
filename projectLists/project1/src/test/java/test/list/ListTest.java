package test.list;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	
	public static void main(String[] args) {
		List<Dog> lists = new ArrayList<>();
		for(int i=0;i<100;i++){
			lists.add(new Dog(i+"号","dog"+i,i));
		}
		System.out.println(lists);
		System.out.println("开始切割集合........");
		List<List<Dog>> listAll = new ArrayList<>(lists.size());
		int index = 0;
		List<Dog> newList = new ArrayList<>(20);
		for(Dog dog:lists){
			newList.add(dog);
			if(index == 20){
				listAll.add(newList);
				newList = new ArrayList<>(20);
				index = 0;
			}
			index++;
		}
		System.out.println(listAll);
	}
	
}

class Dog{
	private String id;
	private String name;
	private Integer age;
	
	public Dog(){}
	
	public Dog(String id,String name,Integer age){
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
