package leetCode.util;

import bookSourceCode.algorithm.Random;
import leetCode.entity.ListNode;

public class ListNodeFactory {


    public static ListNode getInstance(int depth){
        ListNode listNode = new ListNode(1);
        ListNode cur=listNode;
        for(int i=1;i<depth;i++){
            cur.next=new ListNode(i+1);
            cur=cur.next;
        }

     return listNode;
    }

    public static void main(String[] args) {
        ListNode instance = getInstance(10);
        System.out.println(instance);

    }
}
