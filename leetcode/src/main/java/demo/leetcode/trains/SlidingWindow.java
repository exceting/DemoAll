package demo.leetcode.trains;

public class SlidingWindow {

    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        sw.printAllWindows("abcdefg");
    }

    private void printAllWindows(String s) {
        int start = 0;
        int end = 0;

        char[] cs = s.toCharArray();

        int finalIndex = s.length() - 1;

        while (true) {
            if (end == finalIndex) {
                start++;
            } else {
                end++;
            }

            if (start == finalIndex) {
                break;
            }

            // Print current window.
            System.out.println(s.substring(start, end));

        }
    }

}
