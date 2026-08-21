package strings.easy;

import util.Utils;

// https://leetcode.com/problems/valid-palindrome/description
public class ValidPalindrome {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        String s = scanner.nextLine("Enter string: ");
        boolean result = isPalindrome(s);
        Utils.println("Is Palindrome: " + result);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters ignoring case
            if (Character.toLowerCase(s.charAt(left))
                    != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}