package leetCode.num21;


public class Num21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode listNode = new ListNode();
        if(l1==null && l2==null){
            return null;
        }
        setMes(listNode, l1, l2);
        return listNode;

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);


        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(3);
        listNode1.next.next = new ListNode(5);
        listNode1.next.next.next = new ListNode(7);


        Num21 n = new Num21();
        ListNode listNode2 = n.mergeTwoLists(listNode, listNode1);
        System.out.println(listNode2.val);
        System.out.println(listNode2);


    }

    public void setMes(ListNode returnMes, ListNode l1, ListNode l2) {

        if (l1 == null) {
            if (l2 == null) {
                return;
            } else {
                returnMes.val = l2.val;
                if (l2.next == null) {
                    return;
                }
                returnMes.next = new ListNode();
                setMes(returnMes.next, l1, l2.next);
                return;
            }
        }
        if (l2 == null) {
            if (l1 == null) {
                return;
            } else {
                returnMes.val = l1.val;
                if (l1.next == null) {
                    return;
                }
                returnMes.next = new ListNode();
                setMes(returnMes.next, l1.next, l2);
                return;
            }
        }
        if (l1.val <= l2.val) {
            returnMes.val = l1.val;
            returnMes.next = new ListNode();
            setMes(returnMes.next, l1.next, l2);
            return;
        } else {
            returnMes.val = l2.val;
            returnMes.next = new ListNode();
            setMes(returnMes.next, l1, l2.next);
            return;

        }
    }

    public void setMes1(ListNode returnMes, ListNode l1, ListNode l2) {


        if (l1.val <= l2.val) {
            returnMes.val = l1.val;
            returnMes.next = new ListNode();
            if (l1.next == null) {
                returnMes = new ListNode(4);
                return;
            } else {
                setMes1(returnMes.next, l1.next, l2);

            }
        } else {
            returnMes.val = l2.val;
            returnMes.next = new ListNode();
            if (l2.next == null) {
                returnMes = new ListNode(4);
                return;
            } else {
                setMes1(returnMes.next, l1, l2.next);

            }

        }
    }
}

//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }

