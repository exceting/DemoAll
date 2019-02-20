/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题描述：
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {

    public static void main(String[] args) throws Exception {

        int[] nums = {-3, 4, 3, 90};

        int[] result = twoSumV2(nums, 0);

        for (int i : result) {
            System.out.println(i);
        }

    }

    //方法一，常规的嵌套循环，时间复杂度为O(n^2)
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums.length > 0) {
            break_all:
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if ((nums[i] + nums[j]) == target) {
                        result[0] = i;
                        result[1] = j;
                        break break_all;
                    }
                }
            }
        }
        return result;
    }


    //方法二，采用散列表的方式进行，这一种的时间复杂度为O(n)
    public static int[] twoSumV2(int[] nums, int target) {
        int[] result = new int[2];

        if (nums.length > 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int j = target - nums[i];
                if (map.containsKey(j)) {
                    result[0] = map.get(j);
                    result[1] = i;
                    break;
                }
                map.put(nums[i], i);
            }
        }

        return result;
    }

}
