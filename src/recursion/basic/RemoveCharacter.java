package recursion.basic;

import util.Utils;

public class RemoveCharacter {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");
        char target = scanner.next("Enter target char: ").charAt(0);

        String recursiveResult = recursive(str, 0, target);

        StringBuilder result = new StringBuilder();
        recursiveOptimized(str, 0, target, result);

        Utils.println("Recursive         : " + recursiveResult);
        Utils.println("Recursive Optimized: " + result);
    }


    // ---------------------------------------------------------
    // APPROACH 1: RECURSIVE RETURN VALUE
    // ---------------------------------------------------------

    // Time  : O(n²) due to repeated String creation/concatenation
    // Space : O(n) due to recursion stack
    public static String recursive(String str, int index, char target) {

        // Reached the end of the string.
        if (index == str.length()) {
            return "";
        }

        // Keep the current character if it is not the target.
        if (str.charAt(index) != target) {
            return String.valueOf(str.charAt(index))
                    .concat(recursive(str, index + 1, target));
        }

        // Skip the target character.
        return recursive(str, index + 1, target);
    }


    // ---------------------------------------------------------
    // APPROACH 2: OPTIMIZED RECURSIVE + StringBuilder
    // ---------------------------------------------------------

    // Time  : O(n)
    // Space : O(n) for StringBuilder + O(n) recursion stack
    public static void recursiveOptimized(
            String str,
            int index,
            char target,
            StringBuilder result) {

        // Reached the end of the string.
        if (index == str.length()) {
            return;
        }

        // Add the current character only if it is not the target.
        if (str.charAt(index) != target) {
            result.append(str.charAt(index));
        }

        // Process the remaining characters.
        recursiveOptimized(str, index + 1, target, result);
    }
}