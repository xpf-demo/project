package comparea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;


public class Mainlala {
	
	public static void main(String[] args) {
		List<User> lists = getList();
		print(lists);
		Set<User> set = new HashSet<>(lists);
		System.out.println(lists.contains(new User("张三0","zhang0",20,'0')));
		System.out.println(set);
	}
	
	
	public static List<User> getList(){
		List<User> lists = new ArrayList<>();
		for(int i = 0;i<10;i++) {
			lists.add(new User("张三"+i,"zhang"+i,20+i,i%2==0?'0':'1'));
		}
		return lists;
	}
	
	public static void print(List<User> lists) {
		ListIterator<User> lit = lists.listIterator();
		while(lit.hasNext()) {
			
			System.out.println(lit.next());
		}
	}
	

}
