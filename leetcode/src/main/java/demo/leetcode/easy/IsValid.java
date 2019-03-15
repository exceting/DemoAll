/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @lc app=leetcode.cn id=20 lang=java
 * <p>
 * [20] 有效的括号
 * <p>
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 * <p>
 * algorithms
 * Easy (36.22%)
 * Total Accepted:    52K
 * Total Submissions: 141.6K
 * Testcase Example:  '"()"'
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * <p>
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * <p>
 * <p>
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * <p>
 * <p>
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class IsValid {

    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        System.out.println(isValid.isValid("{[]}"));
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }
        int length = s.length();
        if (length % 2 == 0) {
            Stack<Character> stack = new Stack<>();
            Map<Character, Character> map = new HashMap<>();
            map.put('}', '{');
            map.put(']', '[');
            map.put(')', '(');

            for (int i = 0; i < length; i++) {
                char current = s.charAt(i);
                if (map.containsKey(current)) {
                    if (stack.empty()) {
                        return false;
                    }
                    Character upon = stack.pop(); //弹出栈里最上层的数据
                    if (upon == null) {
                        return false;
                    }
                    if (upon != map.get(current)) {
                        return false;
                    }
                } else {
                    stack.push(current);
                }
            }
            return stack.empty();
        }
        return false;
    }

}
