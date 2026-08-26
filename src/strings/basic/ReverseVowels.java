package strings.basic;

import util.Utils;

// https://leetcode.com/problems/reverse-vowels-of-a-string/
public class ReverseVowels {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        String s = scanner.nextLine("Enter string: ");
        String result = reverseVowels(s);
        Utils.println("Result: " + result);
    }

    /*
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseVowels(String s) {

        char[] chars = s.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {

            if (!isVowel(chars[left])) {
                left++;
            }
            else if (!isVowel(chars[right])) {
                right--;
            }
            else {
                // Both are vowels
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;

                left++;
                right--;
            }
        }

        return new String(chars);
    }

    private static boolean isVowel(char ch) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for (char c : vowels) {
            if (Character.toLowerCase(ch) == c) {
                return true;
            }
        }

        return false;
    }

}