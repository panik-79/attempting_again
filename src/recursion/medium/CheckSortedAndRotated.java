package recursion.medium;

import util.Utils;

// https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/
public class CheckSortedAndRotated {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter arr size: ");
        int[] arr = scanner.nextIntArray(n, "Enter arr: ");

        boolean bruteForce = bruteForce(arr);
        boolean myApproach = recursiveSplit(arr);
        boolean recursive = recursive(arr, 0, 0);
        boolean optimal = iterative(arr);

        Utils.println("Brute Force : " + bruteForce);
        Utils.println("My Approach : " + myApproach);
        Utils.println("Recursive   : " + recursive);
        Utils.println("Optimal     : " + optimal);
    }


    // ---------------------------------------------------------
    // APPROACH 1: BRUTE FORCE
    // Time  : O(n²)
    // Space : O(1)
    // ---------------------------------------------------------

    public static boolean bruteForce(int[] arr) {

        int n = arr.length;

        // Try every possible rotation.
        for (int rotation = 0; rotation < n; rotation++) {

            boolean sorted = true;

            // Check whether this rotation is sorted.
            for (int i = 0; i < n - 1; i++) {

                int current = arr[(rotation + i) % n];
                int next = arr[(rotation + i + 1) % n];

                if (current > next) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                return true;
            }
        }

        return false;
    }


    // ---------------------------------------------------------
    // APPROACH 2: MY APPROACH
    // Find the drop, then check both sections.
    // Time  : O(n)
    // Space : O(n) due to recursive isSorted()
    // ---------------------------------------------------------

    public static boolean recursiveSplit(int[] arr) {

        int n = arr.length;
        int dropIndex = -1;

        // Find the first normal drop.
        for (int i = 0; i < n - 1; i++) {

            if (arr[i] > arr[i + 1]) {

                // More than one normal drop means invalid.
                if (dropIndex != -1) {
                    return false;
                }

                dropIndex = i;
            }
        }

        // No normal drop means the array is already sorted.
        if (dropIndex == -1) {
            return true;
        }

        // Check the circular connection after rotation.
        if (arr[n - 1] > arr[0]) {
            return false;
        }

        // Check the section before the drop.
        boolean beforeSorted = isSorted(arr, 0, dropIndex);

        // Check the section after the drop.
        boolean afterSorted = isSorted(arr, dropIndex + 1, n - 1);

        return beforeSorted && afterSorted;
    }


    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static boolean isSorted(int[] arr, int start, int end) {

        // One element is always sorted.
        if (start >= end) {
            return true;
        }

        // Current pair must be sorted.
        if (arr[start] > arr[start + 1]) {
            return false;
        }

        return isSorted(arr, start + 1, end);
    }


    // ---------------------------------------------------------
    // APPROACH 3: RECURSIVE DROP COUNT
    // Time  : O(n)
    // Space : O(n) due to recursion stack
    // ---------------------------------------------------------

    public static boolean recursive(int[] arr, int index, int drops) {

        // More than one drop means invalid.
        if (drops > 1) {
            return false;
        }

        // Check the circular pair: last -> first.
        if (index == arr.length - 1) {

            if (arr[index] > arr[0]) {
                drops++;
            }

            return drops <= 1;
        }

        // Count a drop between the current pair.
        if (arr[index] > arr[index + 1]) {
            drops++;
        }

        // Recursively check the next pair.
        return recursive(arr, index + 1, drops);
    }


    // ---------------------------------------------------------
    // APPROACH 4: OPTIMAL ITERATIVE
    // Time  : O(n)
    // Space : O(1)
    // ---------------------------------------------------------

    public static boolean iterative(int[] arr) {

        int n = arr.length;
        int drops = 0;

        // Check every pair including last -> first.
        for (int i = 0; i < n; i++) {

            int next = (i + 1) % n;

            if (arr[i] > arr[next]) {
                drops++;
            }

            // More than one drop is impossible for valid array.
            if (drops > 1) {
                return false;
            }
        }

        return true;
    }
}