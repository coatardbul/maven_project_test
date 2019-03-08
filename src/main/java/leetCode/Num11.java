package leetCode;

import org.junit.Test;

public class Num11 {

    public int maxArea(int[] height) {
         int maxValue=0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                if(maxValue<Math.min(height[i],height[j])*(j-i)){
                    maxValue=Math.min(height[i],height[j])*(j-i);
                }

            }

        }
        return maxValue;
    }
    @Test
    public void test2(){
        int [] ss={1,8,6,2,5,4,8,3,7};
        int maxValue=maxArea(ss);
        System.out.println(maxValue);
    }
}
