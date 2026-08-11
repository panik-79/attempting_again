package recursion.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Size: ");
        int[] arr = scanner.nextIntArray(n, "Enter array elements: ");

        // Approach 1: Return results from recursion.
        List<List<Integer>> returnResults = recursive(arr, 0);

        // Approach 2: Store results using backtracking.
        List<List<Integer>> backtrackingResults = new ArrayList<>();
        recursiveBacktracking(
                arr,
                0,
                new ArrayList<>(),
                backtrackingResults
        );

        Utils.println("Return Results : " + returnResults);
        Utils.println("Backtracking   : " + backtrackingResults);
    }


    // =========================================================
    // APPROACH 1: RETURN RESULTS
    // =========================================================
    //
    // Idea:
    // Ask recursion to generate all subsets of the smaller
    // remaining array.
    //
    // Then for the current element:
    //
    // 1. Don't take it -> keep smaller results as they are.
    // 2. Take it     -> add current element to every result.
    //
    // Example:
    //
    // arr = [1, 2, 3]
    //
    // recursive(2) gives:
    // [], [3], [2], [2, 3]
    //
    // Don't take 1:
    // [], [3], [2], [2, 3]
    //
    // Take 1:
    // [1], [1, 3], [1, 2], [1, 2, 3]
    //
    // Total = 8 subsets.
    //
    // ---------------------------------------------------------
    // Time  : O(n * 2^n)
    // Space : O(n * 2^n)
    //
    // 2^n subsets are generated.
    // Each subset can contain up to n elements.
    // =========================================================

    public static List<List<Integer>> recursive(
            int[] arr,
            int currIdx) {

        // Empty remaining array has one subset: [].
        if (currIdx == arr.length) {
            List<List<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }

        // Get all subsets of the smaller remaining array.
        List<List<Integer>> smaller =
                recursive(arr, currIdx + 1);

        // Don't take current element.
        List<List<Integer>> result =
                new ArrayList<>(smaller);

        // Take current element.
        for (List<Integer> subset : smaller) {

            List<Integer> withCurrent =
                    new ArrayList<>();

            withCurrent.add(arr[currIdx]);
            withCurrent.addAll(subset);

            result.add(withCurrent);
        }

        return result;
    }


    // =========================================================
    // APPROACH 2: STORE RESULTS + BACKTRACKING
    // =========================================================
    //
    // Idea:
    // Carry one mutable currentSubset downward.
    //
    // At every index:
    //
    // 1. Take the current element.
    // 2. Recurse.
    // 3. Undo the choice (backtrack).
    // 4. Don't take the current element.
    //
    // Pattern:
    //
    //     choose
    //       ↓
    //     recurse
    //       ↓
    //      undo
    //
    // ---------------------------------------------------------
    // Time  : O(n * 2^n)
    // Space : O(n * 2^n)
    //
    // O(2^n) subsets are stored.
    // Recursion stack + currentSubset use O(n).
    // =========================================================

    public static List<List<Integer>> recursiveBacktracking(
            int[] arr,
            int currIdx,
            List<Integer> currentSubset,
            List<List<Integer>> subsets) {

        // Current subset is complete.
        if (currIdx == arr.length) {

            // Copy because currentSubset will be modified later.
            subsets.add(new ArrayList<>(currentSubset));

            return subsets;
        }

        // TAKE current element.
        currentSubset.add(arr[currIdx]);

        recursiveBacktracking(
                arr,
                currIdx + 1,
                currentSubset,
                subsets
        );

        // Undo the TAKE choice.
        currentSubset.removeLast();

        // DON'T TAKE current element.
        recursiveBacktracking(
                arr,
                currIdx + 1,
                currentSubset,
                subsets
        );

        return subsets;
    }
}