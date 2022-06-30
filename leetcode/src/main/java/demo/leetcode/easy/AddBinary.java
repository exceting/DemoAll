/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @lc app=leetcode.cn id=67 lang=java
 * <p>
 * [67] 二进制求和
 * <p>
 * https://leetcode-cn.com/problems/add-binary/description/
 * <p>
 * algorithms
 * Easy (46.10%)
 * Total Accepted:    19K
 * Total Submissions: 40.3K
 * Testcase Example:  '"11"\n"1"'
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字1和0。
 * <p>
 * 示例1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 示例2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("1010", "1011"));
    }

    public String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        int biglen = alen;
        int tinylen = blen;
        String big = a;
        String tiny = b;
        if (alen < blen) {
            biglen = blen;
            big = b;
            tinylen = alen;
            tiny = a;
        }

        Map<String, Integer> result_map = new HashMap<>();
        result_map.put("10", 1);
        result_map.put("01", 1);
        result_map.put("00", 0);
        result_map.put("11", 10);

        int big_cp = biglen - 1;

        int tiny_cp = tinylen - 1;

        int carry = 0; //进位

        StringBuilder sb = new StringBuilder();

        for (int i = big_cp; i >= 0; i--) {
            char bigOne = big.charAt(i);
            char tinyOne = '0';
            if (tiny_cp >= 0) {
                tinyOne = tiny.charAt(tiny_cp);
            }
            int result = result_map.get(bigOne + "" + tinyOne);

            if (i == 0) {
                if (result == 10) {
                    result += carry;
                } else {
                    result = result_map.get(result + "" + carry);
                }
                sb.insert(0, result);
                break;
            }

            if (result == 10) {
                if (carry == 1) {
                    sb.insert(0, 1);
                } else {
                    carry = 1;
                    sb.insert(0, 0);
                }
            } else {
                if (carry == 1) {
                    result = result_map.get(result + "" + carry);
                    if (result == 10) {
                        result = 0;
                    } else {
                        carry = 0;
                    }
                }
                sb.insert(0, result);
            }
            tiny_cp--;
        }

        return sb.toString();

    }

}
