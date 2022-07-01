package demo.leetcode.easy;

/**
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack和needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回-1。
 */
public class StrStr {
    public static void main(String[] args) {
        System.out.println(strStr("abc", "a"));
    }

    public static int strStr(String haystack, String needle) {

        if (needle == null || haystack == null || needle.length() > haystack.length()) {
            return -1;
        }

        if (haystack.equals(needle)) {
            return 0;
        }

        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();

        int p = 0;

        while (true) {
            int j = 0;
            if ((h.length - p) < n.length) {
                return -1;
            }
            for (int i = p; i < h.length; i++) {
                if (n[j] != h[i]) {
                    p += 1;
                    break;
                }
                if (j == n.length - 1) {
                    return p;
                }
                j++;
            }
        }
    }

}
