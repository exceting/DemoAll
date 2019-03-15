/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.medium;

/**
 * 问题描述：
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.add(4).add(3);

        ListNode l2 = new ListNode(5);
        l2.add(6).add(4);

        AddTwoNumbers twoNumbers = new AddTwoNumbers();
        ListNode result = twoNumbers.addTwoNumbers(l1, l2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int a = 0;
        int b = 0;

        int i = 1;
        while (l1 != null) {
            a += l1.val * i;
            l1 = l1.next;
            i *= 10;
        }

        int j = 1;
        while (l2 != null) {
            b += l2.val * j;
            l2 = l2.next;
            j *= 10;
        }

        int c = a + b;

        char[] cArr = String.valueOf(c).toCharArray();
        ListNode result = new ListNode(Integer.parseInt(cArr[cArr.length - 1] + ""));

        ListNode holder = result;
        int r = cArr.length - 2;
        while (r >= 0) {
            holder = holder.add(Integer.parseInt(cArr[r] + ""));
            r--;
        }

        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode add(int val) {
        next = new ListNode(val);
        return next;
    }
}
