package recursion.medium.generation;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/description/
public class GenerateValidParentheses {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter n: ");

        List<String> result = recursive(n,
                0,              // index
                0,              // opening
                0,              // closing
                new StringBuilder(),
                new ArrayList<>()
        );

        Utils.print(result);
    }

    /*
     * Approach: Recursion + Backtracking
     *
     * State:
     * - index   -> current length of string
     * - opening -> number of '(' used
     * - closing -> number of ')' used
     * - curr    -> current generated string
     *
     * Choices:
     * 1. Add '(' if opening < n
     * 2. Add ')' if closing < opening
     *
     * Time:
     * O(C(n) * n)
     * where C(n) is the nth Catalan number
     *
     * Space:
     * O(n) recursion depth
     * + O(C(n) * n) for the answer
     */
    public static List<String> recursive(
            int n,
            int index,
            int opening,
            int closing,
            StringBuilder curr,
            List<String> ans) {

        // Base case:
        // We have placed exactly 2n parentheses.
        if (index == 2 * n) {
            ans.add(curr.toString());
            return ans;
        }

        // Choice 1: Add '('
        // We can use at most n opening parentheses.
        if (opening < n) {

            // Choose
            curr.append("(");

            // Explore
            recursive(n, index + 1, opening + 1,
                    closing, curr, ans);

            // Undo
            curr.deleteCharAt(curr.length() - 1);
        }

        // Choice 2: Add ')'
        // We can only close if there is an unmatched '('.
        if (closing < opening) {

            // Choose
            curr.append(")");

            // Explore
            recursive(n, index + 1, opening,
                    closing + 1, curr, ans);

            // Undo
            curr.deleteCharAt(curr.length() - 1);
        }

        return ans;
    }
}