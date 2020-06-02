package leetCode.num27;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * <p>
 * Note that the order of those five elements can be arbitrary.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        int maxLenght = 1;
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] == val) {
                if (nums[i + 1] == val) {
                    maxLenght = maxLenght + 1;
                } else {
                    for (int j = index; j < nums.length - maxLenght; j++) {
                        nums[j] = nums[j + maxLenght];
                        i = i + 1 - maxLenght;
                        index = index + 1;
                        continue;
                    }
                    maxLenght = 1;
                }
            } else {
                index = index + 1;
            }

            //遍历i
            i = i + 1;
        }
        return index;
    }

    /**
     * [0, 1, 2, 2, 3, 0, 4, 2]
     * 2
     *
     * @param nums
     * @param val
     * @return
     */

    public int removeElement1(int[] nums, int val) {
        if(nums.length==1){
            if(nums[0]==val){
                nums=null;
                return 0;
            }else {
                return 1;
            }
        }
        //重复的最大长度
        int maxLength = 1;
        //重复之前的index
        int index = 0;
        //遍历数组
        int i = 0;

        //重复出现的总数
        int num = 0;
        while (i < nums.length - 1 - num) {
            if (nums[i] == val) {
                if (nums[i + 1] == val) {
                    maxLength++;
                } else {
                    //复制后面的数据，从index 开始，到
                    for (int j = index; j < nums.length - 1 - maxLength; j++) {
                        nums[j] = nums[j + maxLength];
                        index++;
                        i = index;
                        continue;
                    }
                    maxLength = 1;
                }
                num++;
            } else {
                index++;
            }
            i++;
        }
        return index;
    }

    public static void main(String[] args) {
        int[] num = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] num1 = {5};


        Solution s = new Solution();
        s.removeElement1(num1, 2);
        Solution s1 = new Solution();

    }
}