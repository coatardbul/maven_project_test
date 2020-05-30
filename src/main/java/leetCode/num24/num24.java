package leetCode.num24;

import leetCode.entity.ListNode;

/**
 * 24. Swap Nodes in Pairs
 * Medium
 * Given a linked baseJava.list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the baseJava.list's nodes, only nodes itself may be changed.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the baseJava.list as 2->1->4->3.
 */
public class num24 {
    public ListNode swapPairs(ListNode head) {
        ListNode cur = head;

        while (cur!=null && cur.next != null) {
            ListNode node = new ListNode();
            node.val=cur.val;
            cur.val=cur.next.val;
            cur.next.val=node.val;
            cur=cur.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(7);
        num24 n=new num24();
        n.swapPairs(listNode1);

    }
}
