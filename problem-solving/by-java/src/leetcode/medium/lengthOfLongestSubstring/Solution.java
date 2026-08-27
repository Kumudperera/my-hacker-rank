package leetcode.medium.lengthOfLongestSubstring;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) return s.length();

        char ch;
        int start = 0;
        int maxLen = 0;
        Map<Character, Integer> visited = new HashMap<>();

        for(int end = 0; end < s.length(); end++) {
            ch = s.charAt(end);

            if(visited.get(ch) != null && visited.get(ch) >= start) start = visited.get(ch) + 1;
            visited.put(ch, end);

            if(end - start + 1 > maxLen) maxLen = end - start + 1;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int x = lengthOfLongestSubstring("abcabcbb");
        System.out.println(x);
    }
}
