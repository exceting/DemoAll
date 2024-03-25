package demo.leetcode.easy;

/**
 * Q.160
 * {@see https://leetcode.cn/problems/intersection-of-two-linked-lists/description/}
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        //listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]

        ListNode aList1 = new ListNode(4);
        ListNode aList2 = new ListNode(1);

        ListNode aList3 = new ListNode(5);
        ListNode aList4 = new ListNode(6);
        ListNode aList11 = new ListNode(1);

        ListNode aList5 = new ListNode(8);
        ListNode aList6 = new ListNode(4);
        ListNode aList7 = new ListNode(5);

        aList1.next = aList2;
        aList2.next = aList5;

        aList3.next = aList4;
        aList4.next = aList11;
        aList11.next = aList5;

        aList5.next = aList6;
        aList6.next = aList7;

        GetIntersectionNode getIntersectionNode = new GetIntersectionNode();
        ListNode r = getIntersectionNode.getIntersectionNode(aList1, aList3);

        System.out.println("xxxxxx ----->  " + (r == null ? null : r.val));

    }

    // O(n^2)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aNext = headA;
        while (true) {
            if (aNext == null) {
                break;
            }
            ListNode bNext = headB;
            while (true) {
                if (bNext == null) {
                    break;
                }
                if (aNext == bNext) {
                    return aNext;
                }
                bNext = bNext.next;
            }
            aNext = aNext.next;
        }

        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
