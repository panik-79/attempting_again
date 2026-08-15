package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// LeetCode 39
// https://leetcode.com/problems/combination-sum/description/
// https://takeuforward.org/plus/dsa/problems/combination-sum
public class CombinationSum {

    /*
     * ============================================================
     * Problem: Combination Sum
     * ============================================================
     *
     * Given an array of distinct positive integers and a target,
     * generate all unique combinations whose sum equals target.
     *
     * A number can be used MULTIPLE times.
     *
     * Example:
     *
     * candidates = [2, 3, 6, 7]
     * target = 7
     *
     * Output:
     * [
     *     [2, 2, 3],
     *     [7]
     * ]
     *
     * ------------------------------------------------------------
     *
     * Important:
     *
     * [2, 2, 3] is valid.
     *
     * [2, 3, 2] should NOT be generated separately.
     *
     * Order does not matter.
     *
     * Each candidate can be reused any number of times.
     *
     * ------------------------------------------------------------
     *
     * Backtracking Checklist:
     *
     * STATE:
     *     What have I chosen so far?
     *
     * CHOICES:
     *     What can I choose next?
     *
     * BASE CASE:
     *     When is the current combination complete?
     *
     * UNDO:
     *     What needs to be restored after recursion?
     *
     * PRUNING:
     *     When can I prove that this branch cannot work?
     *
     * ============================================================
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter number of candidates: ");

        int[] candidates = new int[n];

        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt(
                    "Enter candidate " + (i + 1) + ": "
            );
        }

        int target = scanner.nextInt("Enter target: ");

        List<List<Integer>> result =
                recursive(candidates, target);

        Utils.println("Combinations: " + result);
        Utils.println("Count: " + result.size());
    }

    public static List<List<Integer>> recursive(
            int[] candidates,
            int target) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(
                candidates,
                0,         // index
                target,         // remaining
                new ArrayList<>(), // current
                result
        );

        return result;
    }

    public static void backtrack(
            int[] candidates,
            int index,
            int remaining,
            List<Integer> current,
            List<List<Integer>> result) {

        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (remaining < 0) {
            // no solution here
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int currNum = candidates[i];
            current.add(currNum);
            backtrack(candidates, i, remaining - currNum, current, result);
            current.removeLast();
        }
    }
}