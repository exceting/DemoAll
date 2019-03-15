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

    public static void main(String[] args){
        MergeTwoLists s = new MergeTwoLists();
        s.mergeTwoLists(null, null);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        AtomicInteger incre = new AtomicInteger(1);
        incre.incrementAndGet();
        incre.incrementAndGet();

        AtomicLong incre2 = new AtomicLong();
        incre2.addAndGet(100L);
        incre2.addAndGet(100L);
        System.out.println(incre.get() +"   "+incre2.get());
        return null;
    }

}

class ListNode {

    int val;

    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
