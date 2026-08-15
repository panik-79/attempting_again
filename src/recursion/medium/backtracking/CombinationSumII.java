package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/description/

public class CombinationSumII {

    /*
     * ============================================================
     * Problem: Combination Sum II
     * ============================================================
     *
     * Given an array of integers candidates and a target integer,
     * return all unique combinations of candidates where the
     * chosen numbers sum to target.
     *
     * IMPORTANT:
     *
     * - Each number may be used AT MOST ONCE.
     * - The input array may contain DUPLICATE values.
     * - The result must not contain duplicate combinations.
     * - Order of elements inside a combination does not matter.
     *
     * ------------------------------------------------------------
     *
     * Example 1:
     *
     * Input:
     * candidates = [10,1,2,7,6,1,5]
     * target = 8
     *
     * Output:
     *
     * [
     *     [1,1,6],
     *     [1,2,5],
     *     [1,7],
     *     [2,6]
     * ]
     *
     * ------------------------------------------------------------
     *
     * Example 2:
     *
     * Input:
     * candidates = [2,5,2,1,2]
     * target = 5
     *
     * Output:
     *
     * [
     *     [1,2,2],
     *     [5]
     * ]
     *
     * ------------------------------------------------------------
     *
     * Important Difference From Combination Sum I:
     *
     * Combination Sum I:
     *
     *     A candidate can be reused.
     *
     *     recurse(i)
     *
     * Combination Sum II:
     *
     *     Each array element can be used only once.
     *
     *     recurse(i + 1)
     *
     * ------------------------------------------------------------
     *
     * Why Sorting?
     *
     * The input can contain duplicate values.
     *
     * Sorting puts duplicates next to each other:
     *
     *     [1,2,1,2,2]
     *
     * becomes:
     *
     *     [1,1,2,2,2]
     *
     * This allows us to skip duplicate choices at the
     * same recursion level.
     *
     * Sorting also allows pruning:
     *
     *     if candidates[i] > remaining
     *
     * then all later candidates are also too large.
     *
     * ------------------------------------------------------------
     *
     * Backtracking Checklist:
     *
     * STATE:
     *     current combination
     *     current index
     *     remaining target
     *
     * CHOICES:
     *     Which candidates can I choose from this index onward?
     *
     * BASE CASE:
     *     When have I found a valid combination?
     *
     * UNDO:
     *     What should I remove after recursion?
     *
     * DUPLICATES:
     *     When should I skip the current candidate?
     *
     * PRUNING:
     *     When can I stop exploring the remaining candidates?
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

        Utils.sortAsc(candidates);

        List<List<Integer>> result = new ArrayList<>();

        backtrack(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }

    public static void backtrack(
            int[] candidates,
            int index,
            int remaining,
            List<Integer> current,
            List<List<Integer>> result) {

        if (candidates[index] > remaining) {
            return;
        }

        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (remaining < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int currNum = candidates[i];
            current.add(currNum);
            backtrack(candidates, i + 1, remaining - currNum, current, result);
            current.removeLast();
        }
    }
}