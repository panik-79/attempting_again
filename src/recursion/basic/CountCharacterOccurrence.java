package recursion.basic;

import util.Utils;

public class CountCharacterOccurrence {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");
        char target = scanner.next("Enter target char: ").charAt(0);

        int count = recursive(str, 0, target);

        Utils.println("Occurrences: " + count);
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static int recursive(String str, int index, char target) {

        // Reached the end of the string.
        if (index == str.length()) {
            return 0;
        }

        // Count current character if it matches the target.
        if (str.charAt(index) == target) {
            return 1 + recursive(str, index + 1, target);
        }

        // Current character doesn't match, so continue searching.
        return recursive(str, index + 1, target);
    }
}