package recursion.medium.backtracking;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combination-sum-iii/description/
// https://takeuforward.org/plus/dsa/problems/combination-sum-iii

public class CombinationSumIII {

    /*
     * ============================================================
     * PROBLEM
     * ============================================================
     *
     * Find all combinations of exactly k DISTINCT numbers from
     * 1 to 9 whose sum is equal to target.
     *
     * Example:
     *
     *     k = 3
     *     target = 7
     *
     *     [1, 2, 4]
     *
     *
     * ============================================================
     * BACKTRACKING PATTERN
     * ============================================================
     *
     * At every level:
     *
     *     1. Choose a number
     *     2. Explore recursively
     *     3. Undo the choice
     *
     *     CHOOSE
     *        ↓
     *     EXPLORE
     *        ↓
     *      UNDO
     *
     *
     * ============================================================
     * STATE
     * ============================================================
     *
     *     index
     *         Where should this level start choosing from?
     *
     *     remainingSum
     *         How much sum is still required?
     *
     *     current
     *         Combination currently being built.
     *
     *     ans
     *         All valid combinations.
     *
     *
     * ============================================================
     * CHOICES
     * ============================================================
     *
     * We can choose any number from:
     *
     *     index ... 9
     *
     *
     * ============================================================
     * WHY i + 1?
     * ============================================================
     *
     * Numbers can be used only once.
     *
     * If we choose:
     *
     *     i = 3
     *
     * then the next recursion starts from:
     *
     *     i + 1
     *
     * Therefore 3 cannot be chosen again.
     *
     * It also guarantees combinations are generated in
     * increasing order:
     *
     *     [1, 3, 5]
     *
     * instead of also generating:
     *
     *     [3, 1, 5]
     *
     *
     * ============================================================
     * BASE CASE
     * ============================================================
     *
     * We need exactly k numbers.
     *
     * Therefore:
     *
     *     current.size() == k
     *
     * At this point:
     *
     *     remainingSum == 0
     *
     * means we found a valid answer.
     *
     *
     * ============================================================
     * PRUNING
     * ============================================================
     *
     * options are sorted:
     *
     *     1 2 3 4 5 6 7 8 9
     *
     * If:
     *
     *     currentNum > remainingSum
     *
     * then currentNum cannot be chosen.
     *
     * More importantly, every number AFTER currentNum is
     * even larger.
     *
     * Therefore we can:
     *
     *     break
     *
     * instead of continue.
     *
     * IMPORTANT:
     *
     * break only stops the CURRENT for-loop.
     * It does NOT stop the entire recursion.
     *
     *
     * ============================================================
     * IMPORTANT DIFFERENCE FROM COMBINATION SUM I
     * ============================================================
     *
     * Combination Sum I:
     *
     *     Number can be reused.
     *
     *     recurse(i)
     *
     * Combination Sum III:
     *
     *     Number can be used only once.
     *
     *     recurse(i + 1)
     *
     *
     * ============================================================
     * NO NEED FOR current.contains()
     * ============================================================
     *
     * We don't need:
     *
     *     if (current.contains(currentNum))
     *
     * because i + 1 already prevents us from going backwards
     * and choosing the same number again.
     *
     *
     * ============================================================
     */

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int k = scanner.nextInt("Enter k: ");
        int target = scanner.nextInt("Enter target: ");

        // Problem constraint: numbers are always 1 to 9.
        List<Integer> options =
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<List<Integer>> ans = new ArrayList<>();

        List<List<Integer>> result = combinationSum3(
                k,
                target,
                options,
                0,
                new ArrayList<>(),
                ans
        );

        Utils.println("Combinations: " + result);
        Utils.println("Count: " + result.size());
    }


    /*
     * ============================================================
     * BACKTRACKING FUNCTION
     * ============================================================
     *
     * k:
     *     Number of elements required.
     *
     * remainingSum:
     *     Sum still required.
     *
     * options:
     *     Available numbers [1 ... 9].
     *
     * index:
     *     Starting position for choices at this level.
     *
     * current:
     *     Current combination being constructed.
     *
     * ans:
     *     Final result.
     */
    public static List<List<Integer>> combinationSum3(
            int k,
            int remainingSum,
            List<Integer> options,
            int index,
            List<Integer> current,
            List<List<Integer>> ans) {

        /*
         * ========================================================
         * BASE CASE
         * ========================================================
         *
         * We have selected exactly k numbers.
         *
         * Only then do we check whether the required sum
         * has also been reached.
         */
        if (current.size() == k) {

            if (remainingSum == 0) {
                // Copy because current will be modified later.
                ans.add(new ArrayList<>(current));
            }

            return ans;
        }


        /*
         * ========================================================
         * CHOICES
         * ========================================================
         *
         * Try every number from index onward.
         */
        for (int i = index; i < options.size(); i++) {

            int currentNum = options.get(i);


            /*
             * ====================================================
             * PRUNING
             * ====================================================
             *
             * Since options are sorted:
             *
             *     currentNum > remainingSum
             *
             * means every later number will also be too large.
             *
             * So stop THIS loop.
             */
            if (currentNum > remainingSum) {
                break;
            }


            /*
             * ====================================================
             * CHOOSE
             * ====================================================
             */
            current.add(currentNum);


            /*
             * ====================================================
             * EXPLORE
             * ====================================================
             *
             * Start from i + 1 because the current number
             * cannot be reused.
             */
            combinationSum3(
                    k,
                    remainingSum - currentNum,
                    options,
                    i + 1,
                    current,
                    ans
            );


            /*
             * ====================================================
             * UNDO / BACKTRACK
             * ====================================================
             *
             * Remove our choice so that the next iteration
             * can try a different number.
             */
            current.removeLast();
        }

        return ans;
    }
}