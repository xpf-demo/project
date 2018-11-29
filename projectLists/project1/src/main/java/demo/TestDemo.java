package demo;

import java.util.Arrays;
import java.util.Scanner;

public class TestDemo {
	
	public static void main(String[] args) {
		/*int[] arr = new int[5];
		int[] newArr = new int[arr.length];
		
		int newIndex = newArr.length-1;
		
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < arr.length; i++) {
			System.out.println("请输入第"+i+"个值:");
			arr[i] = scan.nextInt();
			if(arr[i] > 0) {
				newArr[newIndex-i] = arr[i];
			}else {
				newArr[newIndex-i] = 0;
			}
			
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(newArr));*/
		
		
		System.out.println("-----------------------------");
		
		int[] arr = new int[5];
		int[] newArr = new int[arr.length];
		
		int num;
		Scanner scan = new Scanner(System.in);
		
		for(int i = arr.length-1; i >= 0; i--) {
			arr[i] = scan.nextInt();
			
			if(arr[i] < 0) {
				newArr[4-i] = 0;
			}else {
				newArr[4-i] = arr[i];
			}
			System.out.println(newArr[4-i]);
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(newArr));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
