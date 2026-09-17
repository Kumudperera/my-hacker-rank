package leetcode.medium.longestPalindrome;

public class Solution {
    public static int expand(int i, int j, String s) {
        int left = i;
        int right = j;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static String longestPalindrome(String s) {
        int length;
        int[] temp = {0, 0};

        for (int i = 0; i < s.length(); i++) {
            int oddCentered = expand(i, i, s);

            if (oddCentered > temp[1] - temp[0] + 1) {
                length = oddCentered / 2;
                temp = new int[]{i - length, i + length};
            }

            int evenCentered = expand(i, i + 1, s);

            if (evenCentered > temp[1] - temp[0] + 1) {
                length = (evenCentered / 2) - 1;
                temp = new int[]{i - length, i + 1 + length};
            }
        }

        return s.substring(temp[0], temp[1] + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcd"));
    }
}
