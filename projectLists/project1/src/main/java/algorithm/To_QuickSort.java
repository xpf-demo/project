package algorithm;

public class To_QuickSort {
	
	private static int num = 0;
	
	public static void main(String[] args) {
		int[] a = { 51, 46, 20, 18, 65, 97, 82, 30, 77, 50 };
		System.out.println("数组的初识数据:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
		quick(a,0,a.length-1);
		System.out.println("数组最终排序结果:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
	}
	
	
	
	public static void quick(int[] list,int low,int high){
		if (list.length>0 && low<high) {
			int middle = getMiddle(list, low, high);
			quick(list, low, middle-1);
			quick(list, middle+1, high);
		}
	}
	/**
	* @Title: getMiddle
	* @Description: 选取中间值
	* @param list 数组
	* @param low  最小坐标
	* @param high 最高位坐标
	* @return
	* @throws
	 */
	public static int getMiddle(int[] list,int low,int high){
//		计算执行次数
		num++;
		//选择第一个数作为轴值，存放于临时变量中
		int temp = list[low];
		//保证一致正序选择
		while (low<high) {
			//保证正序的前提下（从左到右），如果右侧大于轴值，则判断右侧数据的下一个
			while (low<high && list[high]>temp) {
				high--;
			}
			//在右侧找到小于temp轴值的数，则进行交换
			list[low]=list[high];
			//保证正序的前提下（从左到右），如果左侧小于轴值，则判断左侧数据的下一个
			while (low<high && list[low]<=temp) {
				low++;
			}
			//在左侧找到大于temp轴值的数，则进行交换
			list[high]=list[low];
		}
		list[low]=temp;
		System.out.println("执行了第"+num+"次");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}
		System.out.println();
		return low;
	}


}
