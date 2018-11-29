package sort;

import java.util.Arrays;
/**
 * @author xpf_d
 * 计数排序算法
 * 适用范围：
 * 1.当数列最大最小值差距过大时，并不适用计数排序。
 * 2.当数列元素不是整数，并不适用计数排序。
 */
public class CountSortAlgorithm {
	
	public static int[] countSort(int[] array) {
		//1.得到数列的最大值和最小值，并计算出差值d
		int max = array[0];
		int min = array[0];
		for(int i = 1; i<array.length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
			if(array[i] < min) {
				min = array[i];
			}
		}
		int d = max - min;
		//2.创建统计数组并统计对应元素个数
		int[] countArray = new int[d+1];
		for(int i = 0; i<array.length; i++) {
			countArray[array[i]-min]++;
		}
		//3.统计数组做变形，后面的元素等于前面的元素之和
		int sum = 0;
		for(int i = 0; i<countArray.length; i++) {
			sum += countArray[i];
			countArray[i] = sum;
		}
		//4.倒序遍历原始数列，从统计数组找到正确位置，输出结果数组
		int[] sortedArray = new int[array.length];
		for(int i = array.length-1; i >= 0; i--) {
			sortedArray[countArray[array[i]-min]-1]=array[i];
			countArray[array[i]-min]--;
		}
		return sortedArray;
	}
	
	public static void main(String[] args) {
		int[] array = new int[] { 95, 94, 91, 98, 99, 90, 99, 93, 91, 92 };
		int[] sortedArray = countSort(array);
		System.out.println(Arrays.toString(sortedArray));
	}

}
