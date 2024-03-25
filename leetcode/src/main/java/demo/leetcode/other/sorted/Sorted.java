package demo.leetcode.other.sorted;

public class Sorted {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 3, 5, 4};
        bubbleSort(arr);
        printArray(arr);
    }

    // 冒泡排序(从大到小)，时间复杂度O(N^2)
    private static void bubbleSort(int[] arr) {
        int i, j = 0;
        for (i = 0; i < arr.length; i++) {
            for (j = i; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int a : arr) {
            sb.append(a).append(",");
        }
        System.out.println(sb);
    }

}
