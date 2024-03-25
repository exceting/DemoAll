package demo.leetcode.medium;

/**
 * Q.5
 * {@see https://leetcode.cn/problems/longest-palindromic-substring/}
 */
public class LongestPalindrome {

    public static void main(String[] args) {

        LongestPalindrome lp = new LongestPalindrome();

        String s = "aca";
        System.out.println("------>" + lp.longestPalindrome(s));
    }

    // O(n^3) 需优化
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int recordStart = 0;
        int recordEnd = 0;
        int recordWindowLength = 0;

        int start = 0;
        int end = 0;

        char[] cs = s.toCharArray();
        int finalIndex = cs.length - 1;

        while (true) {
            if (end == finalIndex) { // 结束一轮，start向前滑动一次
                start++;
                if (start == end) {
                    break;
                }
                end = start + 1;// end归位重新滑动
            } else {
                end++;
            }
            // 判断当前窗口是否为回文(二分法比较)
            boolean h = true;
            int js = start;
            int je = end;
            int loopSize = (je - js + 1) / 2;
            for (int j = 0; j < loopSize; j++) {
                if (cs[js++] != cs[je--]) {
                    h = false;
                    break;
                }
            }

            if (h && (end - start) > recordWindowLength) {
                // 当前窗口是回文，且是目前最大回文，更新窗口
                recordStart = start;
                recordEnd = end;
                recordWindowLength = recordEnd - recordStart;
            }
        }

        if (recordStart == recordEnd) { // 相等说明没有匹配到回文
            // 这里力扣上的测试用例要求返回第一个字母？？例如输入"ac"，输出"a"，不知道为什么🤷
            return s.substring(0, 1);
        }

        return s.substring(recordStart, recordEnd + 1);
    }

}
