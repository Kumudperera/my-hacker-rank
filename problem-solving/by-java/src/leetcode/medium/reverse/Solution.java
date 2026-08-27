package leetcode.medium.reverse;

public class Solution {
    public static int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            long result = (x % 10) + (reversed * 10L);
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
            reversed = (int) result;
            x = x / 10;
        }

        return reversed;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
