package recursion.medium.subsets;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStrings {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter length: ");

        List<String> result = recursive(n, 0);

        Utils.println("Binary strings: " + result);
    }

    // Time  : O(n * 2^n)
    // Space : O(n * 2^n) for storing all generated strings
    public static List<String> recursive(int n, int index) {

        // Base case:
        // When n positions have been processed,
        // there is one valid empty suffix.
        if (index == n) {
            return List.of("");
        }

        // Recursively generate all binary strings
        // for the remaining positions.
        List<String> smaller = recursive(n, index + 1);

        List<String> result = new ArrayList<>();

        // For every smaller result, we have exactly
        // two choices for the current position:
        //
        // 1. Add '0'
        // 2. Add '1'
        //
        // Therefore, every result produces two new results.
        for (String s : smaller) {

            // Choose 0 for the current position.
            result.add("0" + s);

            // Choose 1 for the current position.
            result.add("1" + s);
        }

        return result;
    }
}