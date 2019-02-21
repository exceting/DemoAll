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
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Reverse {

    public static void main(String[] args) {
        int x = -1234;
        //2147483647
        System.out.println(x);
        System.out.println(reverse(x));
    }

    private static int reverse(int x) {

        int r = 0;

        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        while (x != 0) {
            int i = x % 10;
            x /= 10;
            if (r > max / 10 || (r == max / 10 && i > 7)
            || r < min / 10 || (r == min / 10 && i < -8)) {
                return 0;
            }
            r = r * 10 + i;
        }

        return r;
    }

}
