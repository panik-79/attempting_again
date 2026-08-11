package recursion.basic;

import util.Utils;

public class Palindrome {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");

        boolean result = recursive(str, 0, str.length() - 1);

        Utils.println("Is Palindrome: " + result);
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static boolean recursive(String str, int left, int right) {

        // Pointers met or crossed, so all characters matched.
        // left == right for odd length strings
        // left > right for even length strings
        if (left == right || left > right) {
            return true;
        }

        // Compare both ends and recursively move towards the center.
        return str.charAt(left) == str.charAt(right)
                && recursive(str, left + 1, right - 1);
    }
}