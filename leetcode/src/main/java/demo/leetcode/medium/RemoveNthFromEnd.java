package demo.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Q.19
 * {@see https://leetcode.cn/problems/remove-nth-node-from-end-of-list/}
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        // head = [1,2,3,4,5], n = 2
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));

        RemoveNthFromEnd rf = new RemoveNthFromEnd();
        rf.printData(head);
        ListNode headRem = rf.removeNthFromEnd(head, 2);
        rf.printData(headRem);

        ListNode head2 = new ListNode(1, null);
        rf.printData(head2);
        ListNode head2Rem = rf.removeNthFromEnd(head2, 1);
        rf.printData(head2Rem);

        ListNode head3 = new ListNode(1, new ListNode(2, null));
        rf.printData(head3);
        ListNode head3Rem = rf.removeNthFromEnd(head3, 1);
        rf.printData(head3Rem);
    }

    private void printData(ListNode head) {
        if (head == null) {
            System.out.println("null");
        }
        ListNode curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val).append("------>");
            curr = curr.next;
        }
        System.out.println(sb);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 为链表节点编号
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        ListNode curr = head;
        int no = 0;
        while (curr != null) {
            no++;
            nodeMap.put(no, curr);
            curr = curr.next;
        }

        // 计算倒数第n个节点前后位置的节点
        int rank = no - n + 1;
        ListNode prev = nodeMap.get(rank - 1);
        ListNode next = nodeMap.get(rank + 1);

        // 头尾
        if (prev == null && next == null) { // 头尾都是null，说明链表有且仅有一个节点，直接删除返回null
            return null;
        }
        if (prev == null) { // 没有头，说明被删除节点是头，直接返回其next即可
            return next;
        }
        if (next == null) { // 没有尾，说明被删除节点是尾，直接让其上一个元素的next置为null即可
            prev.next = null;
            return head;
        }

        // 正常处理，让prev节点的next指向next节点即可
        prev.next = next;

        return head;
    }

    public static class ListNode {
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

}
