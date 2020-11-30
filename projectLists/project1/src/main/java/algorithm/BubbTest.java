package algorithm;

/**
 * 冒泡排序
 * @author madison
 *
 */
public class BubbTest {

	public static void main(String[] args) {
		// 冒泡排序算法
		int[] numbers = new int[] { 1, 5, 8, 2, 3, 9, 4 };
		// 需进行length-1次冒泡
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = 0; j < numbers.length - 1 - i; j++) {
				if (numbers[j] > numbers[j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
		System.out.println("从小到大排序后的结果是:");
		for (int i = 0; i < numbers.length; i++)
			System.out.print(numbers[i] + " ");

	}

}
