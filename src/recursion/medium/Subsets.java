package recursion.medium;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt("Size : ");
        int[] arr = scanner.nextIntArray(n, "Enter array elements: ");
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(arr, 0, new ArrayList<>(), subsets);
        Utils.println("Subsets: " + subsets);
    }

    public static List<List<Integer>> generateSubsets(
            int[] arr,
            int currIdx,
            List<Integer> currentSubset,
            List<List<Integer>> subsets) {

        // Base condition
        if (currIdx == arr.length) {
            subsets.add(new ArrayList<>(currentSubset));
            return subsets;
        }

        // Take
        currentSubset.add(arr[currIdx]);
        generateSubsets(arr, currIdx + 1, currentSubset, subsets);

        // Backtrack
        currentSubset.remove(currentSubset.size() - 1);

        // Not take
        generateSubsets(arr, currIdx + 1, currentSubset, subsets);

        return subsets;
    }
}