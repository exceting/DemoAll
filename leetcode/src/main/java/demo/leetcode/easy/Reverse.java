/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

/**
 * 问题描述：
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Reverse {

    public static void main(String[] args) {
        int x = 100;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {

        boolean symbol = false;
        if (x < 0) {
            x = x * -1;
            symbol = true;
        }

        int units = x % 10;
        int tens = (x % 100) / 10;
        int hundreds = (x % 1000) / 100;

        int result = (units * 100) + (tens * 10) + hundreds;

        if (symbol) {
            result *= -1;
        }

        if (result < -231 || result > 231) {
            result = 0;
        }

        return result;
    }

}
