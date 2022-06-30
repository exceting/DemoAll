/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

/**
 * @lc app=leetcode.cn id=14 lang=java
 * <p>
 * [14] 最长公共前缀
 * <p>
 * https://leetcode-cn.com/problems/longest-common-prefix/description/
 * <p>
 * algorithms
 * Easy (31.65%)
 * Total Accepted:    55.5K
 * Total Submissions: 172.6K
 * Testcase Example:  '["flower","flow","flight"]'
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * <p>
 * 说明:
 * <p>
 * 所有输入只包含小写字母a-z。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix prefix = new LongestCommonPrefix();
        String s = prefix.longestCommonPrefix(new String[]{"aa", "a"});
        System.out.println("运行结果：" + s);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs != null && strs.length > 0) {
            int limit = strs.length;
            StringBuilder sb = new StringBuilder();
            char[] chars = strs[0].toCharArray();
            int p = 1;
            for (int i = 0; i < chars.length; i++) {
                boolean same = true;
                while (p < limit) {
                    if (strs[p].length() <= i || chars[i] != strs[p].charAt(i)) {
                        same = false;
                        break;
                    }
                    p++;
                }
                if (!same) {
                    break;
                }
                sb.append(chars[i]);
                p = 1;

            }
            return sb.toString();
        }
        return "";
    }

    public String longestCommonPrefixV2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

}