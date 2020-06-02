package leetCode.util;

import java.util.Random;

public class IntArrayFactory {

    public static int[] getArray(int length) {
        return null;
    }

    /**
     * @param repeat         重复的数字
     * @param repeatSize     重复的总大小
     * @param continuousSize 重复连续的大小
     * @param length         数组大小
     * @return
     */
    public static int[] getArray(int repeat, int repeatSize, int continuousSize, int length) {
        int[] result = new int[length];
        if (continuousSize > repeatSize) {
            throw new IllegalArgumentException("重复连续的大小不能大于重复的总大小");
        }
        Random r = new Random();
        //r.nextInt(12)
        return null;
    }

}
