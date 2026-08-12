package recursion.medium.generation;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStringsWithoutConsecutiveOnes {

    /*
     * ============================================================
     * Problem: Generate Binary Strings Without Consecutive Ones
     * ============================================================
     *
     * Given an integer k, generate all binary strings of length k
     * that do NOT contain consecutive '1's.
     *
     * A binary string can contain only:
     *     0 and 1
     *
     * Examples:
     *
     * Input:
     *     k = 3
     *
     * Output:
     *     [000, 001, 010, 100, 101]
     *
     * Invalid:
     *     110
     *     111
     *     011
     *
     * because they contain consecutive 1s.
     *
     * ------------------------------------------------------------
     *
     * Input:
     *     k = 2
     *
     * Output:
     *     [00, 01, 10]
     *
     * ------------------------------------------------------------
     *
     * Input:
     *     k = 1
     *
     * Output:
     *     [0, 1]
     *
     * ------------------------------------------------------------
     *
     * Characters can be reused.
     *
     * The choice for the current position depends on the
     * previously chosen character:
     *
     *     previous = 0
     *         -> can choose 0 or 1
     *
     *     previous = 1
     *         -> can choose only 0
     *
     * ------------------------------------------------------------
     *
     * Your task:
     *
     * 1. Figure out what additional information the recursive
     *    function needs besides k.
     *
     * 2. Define the recursive function's contract.
     *
     * 3. Determine the base condition.
     *
     * 4. Determine the smaller recursive problem after choosing
     *    0 or 1.
     *
     * ============================================================
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int k = scanner.nextInt("Enter length: ");

        List<String> allowedChars = List.of("0", "1");
        List<String> result = recursive(k);

        Utils.println("Valid strings: " + result);
        Utils.println("Count: " + result.size());
    }

    public static List<String> recursive(int k) {

        // One valid string of length 0: the empty string.
        if (k == 0) {
            return List.of("");
        }

        List<String> smaller = recursive(k - 1);
        List<String> result = new ArrayList<>();

        for (String s : smaller) {

            // 0 is always safe.
            result.add(s + "0");

            // 1 is safe only when the previous character is 0.
            if (s.isEmpty() || s.charAt(s.length() - 1) == '0') {
                result.add(s + "1");
            }
        }

        return result;
    }
}