package arrays.medium;

import util.Utils;

// https://leetcode.com/problems/valid-anagram/
public class ValidAnagram {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        String s1 = scanner.next();
        String s2 = scanner.next();

        boolean isValidAnagram = isValidAnagram(s1, s2);
        Utils.println("isValidAnagram: " + isValidAnagram);
    }

    public static boolean isValidAnagram(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        int[] freq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
