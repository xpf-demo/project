package demo;

public class Solution {
	
	public static int[] twoSum(int[] nums, int target) {
        int[] arry = new int[2];
        for(int i = 0; i<nums.length; i++){
            for(int j = i+1; j< nums.length; j++){
                if(nums[i]+nums[j] == target){
                  arry[0] = i;
                  arry[1] = j;
                }
            }
        }
        return arry;
    }
	
	public static void main(String[] args) {
		int[] nums = {12,9,20,50,49,25};
		int target = 13;
		System.out.println(twoSum(nums, target)[0]);
		System.out.println(twoSum(nums, target)[1]);
	}

}
