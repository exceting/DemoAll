package demo.leetcode.easy;

/**
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement {

    public static void main(String[] args) {
        removeDuplicates(new int[]{1, 1, 1, 2, 3, 4, 5, 1, 1}, 1);
    }

    public static int removeDuplicates(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }

        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                p += 1;
                nums[i] = 0;
                continue;
            }
            if (p > 0) {
                nums[i - p] = nums[i];
                nums[i] = 0;
            }
        }

        return nums.length - p;
    }

}
