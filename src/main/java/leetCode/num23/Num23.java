package leetCode.num23;


import leetCode.entity.ListNode;
import lombok.val;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class Num23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length > 0) {
            ListNode returnResult = new ListNode(-1);
            ListNode l1 = returnResult;

            // l1= lists[0];
            int num = 0;
            while (true) {
                for (int i = 0; i < lists.length; i++) {
                    if (lists[i] == null) {
                        continue;
                    }
                    if (l1.val > lists[i].val) {
                        lists[i] = lists[i].next;
                        if (lists[i] == null) {
                            num++;
                        }
                    }
                    l1 = l1.next;
                }
                if (num == lists.length) {
                    break;
                }

            }
            return returnResult.next;

        } else {
            return null;
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
//}