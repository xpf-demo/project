package json;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class Test {
	public static void main(String[] args) {
		
		List<Dog> dogs = new ArrayList<>();
		dogs.add(0, new Dog("1", "xiaowang"));
		dogs.add(1, new Dog("2", "xiaohuang"));
		
		Hh hh = new Hh();
		hh.setLists(dogs);
		
		System.out.println(JSONArray.toJSON(hh));
	}

}


class Hh{
	private List<Dog> lists;
	public List<Dog> getLists() {
		return lists;
	}

	public void setLists(List<Dog> lists) {
		this.lists = lists;
	}
}



class Dog{
	private String id;
	private String name;
	
	public Dog(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
