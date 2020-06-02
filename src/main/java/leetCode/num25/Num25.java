package leetCode.num25;

import leetCode.util.ListNodeFactory;
import leetCode.entity.ListNode;

import java.util.Stack;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class Num25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur=head;
        ListNode curChange=head;
        Boolean flag=false;
        Stack s;
        while (true){
            s=new Stack();
            for(int i=0;i<k;i++){
                if(cur!=null){
                    s.push(cur.val);
                    cur=cur.next;
                }else {
                    flag=true;
                    break;
                }
            }
            if(flag){
                break;
            }
            while (!s.empty()){
                curChange.val= (int) s.pop();
                curChange=curChange.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode instance = ListNodeFactory.getInstance(10);
        Num25 n=new Num25();
        ListNode node = n.reverseKGroup(instance, 3);
        System.out.println(node);
    }

}
