package demo.leetcode.medium;

/**
 * Q.560
 * {@see https://leetcode.cn/problems/subarray-sum-equals-k/?envType=study-plan-v2&envId=top-100-liked}
 */
public class SubarraySum {

    public static void main(String[] args) {
        SubarraySum s = new SubarraySum();
        System.out.println(s.subarraySum(new int[]{1,-1,0},0));
    }

    // 暴力破解
    public int subarraySum(int[] nums, int k) {
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    r++;
                }
            }
        }
        return r;
    }

    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        for(int i = 0; i<nums.length;i++){
            int sum = 0;
            for(int j = i; j<nums.length; j++){
                sum+=nums[j];
                if(sum == k){
                    res++;
                }
            }
        }
        return res;
    }

}
