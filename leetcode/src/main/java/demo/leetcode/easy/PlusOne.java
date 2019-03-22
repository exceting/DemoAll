/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

import java.util.Arrays;

/**
 * @lc app=leetcode.cn id=66 lang=java
 * <p>
 * [66] 加一
 * <p>
 * https://leetcode-cn.com/problems/plus-one/description/
 * <p>
 * algorithms
 * Easy (37.31%)
 * Total Accepted:    41.8K
 * Total Submissions: 110.5K
 * Testcase Example:  '[1,2,3]'
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] data = {0};
        System.out.println(Arrays.toString(plusOne.plusOne(data)));
    }

    public int[] plusOne(int[] digits) {
        int cp = digits.length - 1;
        int carry = 0; //进位
        int[] result = new int[digits.length + 1];
        for (int i = cp; i >= 0; i--) {
            int current = digits[i] + (cp == i ? 1 : 0) + carry;
            if (current > 9 && i > 0) {
                carry = current / 10; //进位
                result[i + 1] = current % 10; //个位赋值
            } else {
                if (i == 0 && current > 9) {
                    result[i] = current / 10;
                    result[i + 1] = current % 10;
                } else {
                    result[i + 1] = current;
                    carry = 0;
                }
            }
        }

        if (result[0] == 0) {
            int[] newResult = new int[digits.length];
            for (int i = 0; i < digits.length; i++) {
                newResult[i] = result[i + 1];
            }
            return newResult;
        }
        return result;
    }
}
