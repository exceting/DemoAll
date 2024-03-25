package demo.leetcode.medium;

public class MaxArea {

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        System.out.println("=====> " + maxArea.maxArea(new int[]{1, 1}));
    }

    // 二次优化，时间复杂度：O(N)
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0]; // 1 * height
        }

        // 取开始指针，结束指针
        int s = 0;
        int e = height.length - 1;
        int maxArea = 0;

        while (true) {
            if (s == e) {
                break;
            }

            maxArea = Math.max(maxArea, Math.abs(e - s) * Math.min(height[e], height[s]));

            // 短的移动
            if (height[s] < height[e]) {

                s++;
            } else {
                e--;
            }
        }
        return maxArea;
    }

    // 暴力破解，时间复杂度O(N^2)，判定超时
    public int maxArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0]; // 1 * height
        }

        int maxArea = 0;

        // 暴力破解
        for (int i = 0; i < height.length; i++) {
            int jStart = i + 1;
            if (jStart >= height.length) {
                break;
            }
            for (int j = jStart; j < height.length; j++) {
                // 任意2个计算面积
                int area = Math.abs(j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    interface X {
        static void xx() {
        }

        default void vb() {

        }

        void vc();
    }

}
