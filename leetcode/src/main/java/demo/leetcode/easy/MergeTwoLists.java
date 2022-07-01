package demo.leetcode.easy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @lc app=leetcode.cn id=21 lang=java
 * <p>
 * [21] 合并两个有序链表
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 * <p>
 * algorithms
 * Easy (52.17%)
 * Total Accepted:    49.1K
 * Total Submissions: 92.7K
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 * <p>
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        MergeTwoLists s = new MergeTwoLists();
        ListNode result = s.mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(4))), new ListNode(1, new ListNode(3, new ListNode(4))));
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode result = new ListNode(0);
        ListNode p = result;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            } else {
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return result.next;
    }

    static class ListNode {
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

