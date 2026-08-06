package arrays.basic;

import util.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicateII {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();

        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int maxIndexDistance = scanner.nextInt();

        Utils.println("Input array: " + Arrays.toString(arr));

        boolean containsDuplicate =
                containsDuplicateInRange(arr, maxIndexDistance);

        Utils.println("Contains Duplicate: " + containsDuplicate);
    }

    /**
     * Returns true if there exist two equal elements whose index difference
     * is at most {@code maxIndexDistance}.
     *
     * Approach:
     * Maintain the most recent index of every element.
     * For each element:
     * 1. If it has been seen before, check the distance from its previous occurrence.
     * 2. If the distance is within the allowed limit, return true.
     * 3. Otherwise, update its most recent index.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     */
    public static boolean containsDuplicateInRange(int[] arr, int maxIndexDistance) {
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();

        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {

            Integer previousIndex = lastSeenIndex.get(arr[currentIndex]);

            // Duplicate found within the allowed distance.
            if (previousIndex != null &&
                    currentIndex - previousIndex <= maxIndexDistance) {
                return true;
            }

            // Record the latest occurrence of the current element.
            lastSeenIndex.put(arr[currentIndex], currentIndex);
        }

        return false;
    }

    /**
     * Space-optimized solution using a sliding window.
     *
     * The HashSet stores only the elements present in the last
     * {@code maxIndexDistance} indices. If an element already exists in
     * the window, a valid duplicate has been found.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(min(n, maxIndexDistance))
     */
    public static boolean containsDuplicateInRangeSpaceOptimized(
            int[] arr,
            int maxIndexDistance
    ) {
        Set<Integer> window = new HashSet<>();

        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {

            // Duplicate found inside the current sliding window.
            if (!window.add(arr[currentIndex])) {
                return true;
            }

            // Remove the element that falls out of the window.
            if (window.size() > maxIndexDistance) {
                window.remove(arr[currentIndex - maxIndexDistance]);
            }
        }

        return false;
    }
}