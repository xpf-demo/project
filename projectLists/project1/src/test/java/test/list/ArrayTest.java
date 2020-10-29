package test.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayTest {
	
	public static void main(String[] args) {
		
		System.out.println(deal("1,5,3,4", "5"));

	}
	
	
	private static String deal(String houseTypeStrs, String yaohaoTypeStr) {
		String[] houseTypeStrsArr = houseTypeStrs.split(",");
		List<String> lists = new LinkedList<>(Arrays.asList(houseTypeStrsArr));
		int startlen = lists.size();
		Iterator<String> it = lists.iterator();
		while(it.hasNext()) {
			String next = it.next();
			if(yaohaoTypeStr.equals(next)) {
				it.remove();
			}
		}
		int endlen = lists.size();
		if(endlen != startlen) {
			lists.add(0, yaohaoTypeStr);
		}
		 return lists.toString().replace("[", "").replace("]", "").replace(" ", "");
	}
	
}
