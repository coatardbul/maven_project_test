package leetCode.num21;

public class MethodTest {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode();
        setMes(listNode.next);
        setMes1(listNode);
        System.out.println(listNode);
    }

    public static void setMes(ListNode listNode) {
        ListNode l1 = new ListNode(2);
        listNode = l1;
    }

    public static void setMes1(ListNode listNode) {
        ListNode l1 = new ListNode(4);
        listNode.next = l1;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
