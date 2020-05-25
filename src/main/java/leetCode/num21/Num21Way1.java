package leetCode.num21;

public class Num21Way1 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;


    }
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);


        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(7);


        ListNode listNode2 = mergeTwoLists(listNode, listNode1);
        System.out.println(listNode2.val);
        System.out.println(listNode2);


    }
}
