package algorithm;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
	/*	Map<String, Object> map = new HashMap<>();
		map.put(null, 0);
		map.put(null, 1);
		map.put(null, 6);
		map.put(null, 4);
		map.put(null, 1);
		map.put(null, 3);
		map.put("", null);
		System.out.println("hashMap:"+map);
		
		Map<String, Object> hashTable = new Hashtable<>();
		hashTable.put("1", 0);
		hashTable.put("2", 0);
		hashTable.put("2", 1);
		System.out.println("hashtable:"+hashTable);
		
		Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
		concurrentMap.put(null, 0);
		System.out.println("concurrentMap:"+concurrentMap);*/
		
		
		
		
		/*String str1 = new String("1");
		String str2 = str1.intern();
		//该行代码首先查看"1"字符串有没有存在在常量池中，此时存在则直接返回该常量，这里返回后没有引用接受他，
		//【假如不存在的话在 jdk1.6中会在常量池中建立该常量，在jdk1.7以后会把堆中该对象的引用放在常量池中】
		String str3 = "1";
		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str2 == str3);
		
		
		String str4 = new String("2")+new String("2");
		str4.intern();
		String str5 = "22";
		System.out.println(str4==str5);*/
		
		
		Integer a = 1;
		String b = "java";
		char b1 = '1';
		Map<String, Object> map = new HashMap<>();
		map.put("c", 0);
		
		
		User user = new User(20);
		
		change(map, a, b,b1,user);
		System.out.println(a);
		System.out.println(b);
		System.out.println(b1);
		System.out.println(map.get("c"));
		Integer i = 128;
		Integer j = 128;
		System.out.println(i==j);
		
		
		System.out.println(user);
		
		
	}
	
	
	public static void change(Map<String, Object> map , Integer i, String s,char b1, User user) {
		i++;
		s = "hello java";
		b1 = '2';
		int c = (int)map.get("c");
		map.put("c", ++c);
	}
	

}


 final class User{
	
	private final int age;

	public User(int age) {
		super();
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "User [age=" + age + "]";
	}
	
	
}
