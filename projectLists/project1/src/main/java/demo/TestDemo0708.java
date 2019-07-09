package demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestDemo0708 {
	
	public static void main(String[] args) {
		/**1.坑一*/
		Object a = (1 == 1) ? new Integer(3) : new Float(1);
		System.out.println(a);
		
		/**2.坑二*/
		Set<Integer> setList = new HashSet<>();
		for(int i = 0; i < 100; i++) {
			setList.add(i);
			setList.remove(i-1);
		}
		
		System.out.println(setList);
		System.out.println(setList.size());
		
		Set<Short> set = new HashSet<>();
		for(short i = 0; i < 100; i++) {
			set.add(i);
			set.remove((short)(i-1));
		}
		
		System.out.println(set);
		System.out.println(set.size());
		
		
		List<String> lists = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			lists.add("我是"+i+"号");
		}
		System.out.println("删除之前："+lists);
		
		Iterator<String> it = lists.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			it.remove();
		}
		System.out.println("删除之后："+lists);
	}

}
