package demo.leetcode.easy;

/**
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持一致。
 * <p>
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有k个元素，那么nums的前k个元素应该保存最终结果。
 * <p>
 * 将最终结果插入nums 的前 k 个位置后返回 k 。
 * <p>
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 0, 0, 0, 3, 3}; // 输入数组
        int[] expectedNums = new int[]{-1, 0, 3}; // 长度正确的期望答案

        int k = removeDuplicates(nums); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    public static int removeDuplicates(int[] nums) {

        if (nums == null) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int p = 0; //记录重复个数，方便后续元素"进位"
        Integer prev = null; // 记录上一个元素
        for (int i = 0; i < nums.length; i++) {
            if (prev != null && nums[i] == prev) { // 当前元素 = prev，需要去重
                prev = nums[i]; // 刷新之前的元素
                p += 1; // "进位"
                nums[i] = 0; // 当前位置0
                continue;
            }
            if (p > 0) { // 当前元素 ≠ prev，且之前已经有重复元素被擦除了，这时需要将当前值刷新到"进位"的地方，后续不重复元素都是如此
                nums[i - p] = nums[i];
                prev = nums[i]; // 刷新之前的元素
                nums[i] = 0; // 当前位置0
                continue;
            }
            prev = nums[i]; // 正常情况下，无脑刷新prev即可
        }

        return nums.length - p;
    }

}
