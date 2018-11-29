package sort;

import java.util.Arrays;
/**
 * @author xpf_d
 * ���������㷨
 * ���÷�Χ��
 * 1.�����������Сֵ������ʱ���������ü�������
 * 2.������Ԫ�ز����������������ü�������
 */
public class CountSortAlgorithm {
	
	public static int[] countSort(int[] array) {
		//1.�õ����е����ֵ����Сֵ�����������ֵd
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
		//2.����ͳ�����鲢ͳ�ƶ�ӦԪ�ظ���
		int[] countArray = new int[d+1];
		for(int i = 0; i<array.length; i++) {
			countArray[array[i]-min]++;
		}
		//3.ͳ�����������Σ������Ԫ�ص���ǰ���Ԫ��֮��
		int sum = 0;
		for(int i = 0; i<countArray.length; i++) {
			sum += countArray[i];
			countArray[i] = sum;
		}
		//4.�������ԭʼ���У���ͳ�������ҵ���ȷλ�ã�����������
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
