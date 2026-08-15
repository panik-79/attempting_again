package recursion.medium.permutations;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// LeetCode 46
// https://leetcode.com/problems/permutations/description/

public class GeneratePermutations {

    /*
     * ============================================================
     * Problem: Generate All Permutations
     * ============================================================
     *
     * Given a string containing distinct characters, generate
     * all possible permutations.
     *
     * Example:
     *
     * Input:
     *     ABC
     *
     * Output:
     *     [ABC, ACB, BAC, BCA, CAB, CBA]
     *
     * Number of permutations:
     *     n!
     *
     * Characters are distinct and every character must be used
     * exactly once.
     *
     * ============================================================
     */


    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        String str = scanner.next("Enter string: ");

        /*
         * ========================================================
         * Approach 1
         * ========================================================
         *
         * Solve the smaller problem first.
         *
         * Example:
         *
         *     ABC
         *      ↓
         *     BC
         *      ↓
         *      C
         *
         * Get permutations of "BC":
         *
         *     [BC, CB]
         *
         * Then insert A at every possible position.
         *
         *     BC → ABC, BAC, BCA
         *     CB → ACB, CAB, CBA
         *
         * ========================================================
         */

        List<String> result1 = recursive(str, 0);

        Utils.println("Approach 1:");
        Utils.println(result1);
        Utils.println("Count: " + result1.size());


        /*
         * ========================================================
         * Approach 2 — Backtracking
         * ========================================================
         *
         * Build one permutation step by step.
         *
         *     Choose
         *       ↓
         *     Recurse
         *       ↓
         *     Undo
         *
         * sb      → current permutation
         * visited → which characters are already used
         *
         * ========================================================
         */

        boolean[] visited = new boolean[str.length()];
        List<String> result2 = new ArrayList<>();

        generate(
                str,
                new StringBuilder(),
                visited,
                result2
        );

        Utils.println("\nApproach 2 — Backtracking:");
        Utils.println(result2);
        Utils.println("Count: " + result2.size());
    }


    /*
     * ============================================================
     * APPROACH 1
     * Smaller problem → insert current character
     * ============================================================
     *
     * recursive(str, idx)
     *
     * Returns all permutations of:
     *
     *     str[idx ... end]
     *
     * ============================================================
     */

    public static List<String> recursive(String str, int idx) {

        // One valid permutation of zero characters is "".
        if (idx == str.length()) {
            return List.of("");
        }

        // Solve smaller problem first.
        List<String> smaller = recursive(str, idx + 1);

        List<String> result = new ArrayList<>();

        String currentChar = String.valueOf(str.charAt(idx));

        for (String s : smaller) {

            // A string of length m has m + 1 insertion positions.
            for (int i = 0; i <= s.length(); i++) {

                String permutation =
                        s.substring(0, i)
                                + currentChar
                                + s.substring(i);

                result.add(permutation);
            }
        }

        return result;
    }


    /*
     * ============================================================
     * APPROACH 2
     * Classic Backtracking
     * ============================================================
     *
     * At every level:
     *
     *     1. Choose an unused character
     *     2. Add it to current permutation
     *     3. Recurse
     *     4. Undo the choice
     *
     * ============================================================
     */

    public static void generate(
            String str,
            StringBuilder current,
            boolean[] visited,
            List<String> result) {

        // A complete permutation has been constructed.
        if (current.length() == str.length()) {
            result.add(current.toString());
            return;
        }

        // Try every character as the next choice.
        for (int i = 0; i < str.length(); i++) {

            // Cannot use the same character twice.
            if (visited[i]) {
                continue;
            }

            // Choose
            current.append(str.charAt(i));
            visited[i] = true;

            // Explore
            generate(str, current, visited, result);

            // Undo
            current.deleteCharAt(current.length() - 1);
            visited[i] = false;
        }
    }
}