package leetCode;

import org.junit.Test;

import java.util.Arrays;

public class Num1 {
    /**
     * Given an array of integers,
     * return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution,
     * and you may not use the same element twice.
     * Example:
     * <p>
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int [] result=new int[2];
        for (int i=nums.length-1;i>=0;i--) {
            for(int j=0;j<i;j++){
                if(nums[j]+nums[i]==target){
                    result[0]=j;
                    result[1]=i;
                }
            }
        }
        return result;
    }


    @Test
    public  void test1(){
        int []nums={2, 7, 11, 15};
        int []arr=twoSum( nums,9);
        System.out.println(Arrays.toString(arr));

    }
}
