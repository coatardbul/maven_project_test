package leetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Num2 {
    /**
     * 2. Add Two Numbers
     * You are given two non-empty linked lists representing
     * two non-negative integers.（非负整数）
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero,
     * except the number 0 itself.
     * <p>
     * Example:
     * <p>
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     */
    public List<Integer> addTwoNumbers(List<Integer> l1, List<Integer> l2) {
        List<Integer> resultList = new ArrayList<>();
        int num=0;
        if (l1.size() == l2.size()) {
            for (int i = 0; i < l1.size(); i++) {
                if(num==0){
                    if (l1.get(i) + l2.get(i) < 10) {
                        resultList.add(l1.get(i)+l2.get(i));
                    } else {
                        resultList.add((Integer)(l1.get(i)+l2.get(i))%10);
                        if(i==resultList.size()-1){
                            resultList.add(1);
                        }
                        num=1;
                    }
                }else {
                    if (l1.get(i) + l2.get(i)+1 < 10) {
                        resultList.add(l1.get(i)+l2.get(i)+1);
                        num=0;
                    } else {
                        resultList.add((Integer)(l1.get(i)+l2.get(i)+1)%10);
                        if(i==resultList.size()-1){
                            resultList.add(1);
                        }
                    }
                }
            }
        }

        return resultList;

    }

    @Test
    public void test1() {
        List<Integer> resultList = new ArrayList<>();
        resultList.add(11);
        resultList.add(22);
        resultList.add(33);
        resultList.add(44);
        resultList.add(55);
        resultList.add(66);

       // System.out.println(resultList.get(1));

        int i=10%3;
        System.out.println(i);


    }
}
