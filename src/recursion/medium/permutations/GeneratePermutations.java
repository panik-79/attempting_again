package recursion.medium.permutations;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations/description
public class GeneratePermutations {

    /*
     * ============================================================
     * Problem: Generate All Permutations
     * ============================================================
     *
     * Given a string containing distinct characters, generate
     * all possible permutations of the string.
     *
     * A permutation is an arrangement of all characters where
     * the order of the characters can change.
     *
     * Each character must be used exactly once.
     *
     * ------------------------------------------------------------
     *
     * Example 1:
     *
     * Input:
     *     ABC
     *
     * Output:
     *     [ABC, ACB, BAC, BCA, CAB, CBA]
     *
     * Number of permutations:
     *     3! = 6
     *
     * ------------------------------------------------------------
     *
     * Example 2:
     *
     * Input:
     *     AB
     *
     * Output:
     *     [AB, BA]
     *
     * ------------------------------------------------------------
     *
     * Example 3:
     *
     * Input:
     *     A
     *
     * Output:
     *     [A]
     *
     * ------------------------------------------------------------
     *
     * Important:
     *
     * - All characters are distinct.
     * - Every character must be used exactly once.
     * - Order matters.
     *
     * ------------------------------------------------------------
     *
     * Approach:
     *
     * Solve the smaller problem first.
     *
     * For example:
     *
     *     ABC
     *      ↓
     *     BC
     *      ↓
     *      C
     *
     * Once we have all permutations of "BC", insert "A"
     * at every possible position in every smaller permutation.
     *
     * Example:
     *
     *     smaller = [BC, CB]
     *
     * Insert A into BC:
     *
     *     ABC
     *     BAC
     *     BCA
     *
     * Insert A into CB:
     *
     *     ACB
     *     CAB
     *     CBA
     *
     * ------------------------------------------------------------
     *
     * Base Case:
     *
     * When there are no characters left, return [""]
     * instead of [].
     *
     * The empty string represents one valid permutation of
     * zero characters and gives the previous call something
     * into which it can insert its character.
     *
     * ------------------------------------------------------------
     *
     * Time Complexity:
     *
     * There are n! permutations and every final string has
     * length n.
     *
     *     O(n * n!)
     *
     * This includes the cost of constructing the output strings.
     *
     * ------------------------------------------------------------
     *
     * Space Complexity:
     *
     * O(n * n!) including the returned result.
     *
     * ============================================================
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");

        List<String> result = recursive(str, 0);

        Utils.println("Permutations: " + result);
        Utils.println("Count: " + result.size());
    }

    /*
     * recursive(str, idx)
     *
     * Returns all permutations of the substring:
     *
     *     str[idx ... end]
     *
     * Example:
     *
     *     recursive("ABC", 1)
     *
     * means:
     *
     *     Generate all permutations of "BC".
     */
    public static List<String> recursive(String str, int idx) {

        // No characters remaining.
        // There is one valid permutation: the empty string.
        if (idx == str.length()) {
            return List.of("");
        }

        // First, solve the smaller problem.
        List<String> smaller = recursive(str, idx + 1);

        List<String> result = new ArrayList<>();

        String currentChar = String.valueOf(str.charAt(idx));

        /*
         * Insert the current character into every possible position of every smaller permutation.
         * For "BC", there are 3 possible positions for A:
         *
         *     |BC
         *     B|C
         *     BC|
         */
        for (String s : smaller) {
            // A string of length m has m + 1 insertion positions.
            for (int i = 0; i <= s.length(); i++) {
                String permutation = s.substring(0, i) + currentChar + s.substring(i);
                result.add(permutation);
            }
        }

        return result;
    }
}