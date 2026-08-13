package recursion.medium.generation;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryStringsWithKOnes {

    /*
     * Problem:
     * Given n and k, generate all binary strings of length n
     * containing exactly k ones.
     *
     * Example:
     *
     * n = 3, k = 2
     * [011, 101, 110]
     *
     * n = 4, k = 2
     * [0011, 0101, 0110, 1001, 1010, 1100]
     *
     * Number of results = C(n, k)
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter length: ");
        int k = scanner.nextInt("Enter number of ones: ");

        List<String> result = recursive(n, k);

        Utils.println("Strings: " + result);
        Utils.println("Count: " + result.size());
    }

    /*
     * recursive(n, k):
     * Generates strings of length n containing exactly k ones.
     *
     * Time Complexity : O(n * C(n, k))
     * Space Complexity: O(n * C(n, k)) including output
     */
    public static List<String> recursive(int n, int k) {

        // Impossible: not enough positions to place k ones.
        if (k > n) {
            return new ArrayList<>();
        }

        // One valid string of length 0: the empty string.
        if (n == 0) {
            return List.of("");
        }

        // No ones remaining: all remaining positions must be zero.
        if (k == 0) {
            return List.of("0".repeat(n));
        }

        List<String> result = new ArrayList<>();

        // Choose 1: one required one is consumed.
        List<String> smallerWithOne = recursive(n - 1, k - 1);

        for (String s : smallerWithOne) {
            result.add("1" + s);
        }

        // Choose 0: number of required ones stays the same.
        List<String> smallerWithZero = recursive(n - 1, k);

        for (String s : smallerWithZero) {
            result.add("0" + s);
        }

        return result;
    }
}