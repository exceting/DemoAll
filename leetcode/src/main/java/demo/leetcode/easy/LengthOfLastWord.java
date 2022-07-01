package demo.leetcode.easy;

/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord2("sun qin wen"));
    }

    public static int lengthOfLastWord(String s) {
        if (!s.contains(" ")) {
            return s.length();
        }
        String[] ss = s.split(" ");
        String r = null;
        for (String str : ss) {
            if (str != null && !str.equals("") && !str.equals(" ")) {
                r = str;
            }
        }
        return r == null ? 0 : r.length();
    }

    public static int lengthOfLastWord2(String s) {
        if (!s.contains(" ")) {
            return s.length();
        }
        char[] cs = s.toCharArray(); // 真晦气，运行时间超1ms

        char prev = ' ';
        int p = 0;
        for (char c : cs) {
            if (c != ' ' && prev == ' ') {
                p = 0;
            }
            if (c != ' ') {
                p++;
            }
            prev = c;
        }

        return p;
    }

}
