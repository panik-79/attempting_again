package recursion.basic;

import util.Utils;

public class MaximumInArray {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter arr size: ");
        int[] arr = scanner.nextIntArray(n, "Enter arr: ");

        int result = recursive(arr, n);

        Utils.println("Maximum: " + result);
    }


    // ---------------------------------------------------------
    // APPROACH 1: BRUTE FORCE / ITERATIVE
    // Time  : O(n)
    // Space : O(1)
    // ---------------------------------------------------------

    public static int iterative(int[] arr) {

        int max = arr[0];

        // Compare every element with the current maximum.
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }


    // ---------------------------------------------------------
    // APPROACH 2: RECURSIVE
    // Time  : O(n)
    // Space : O(n) due to recursion stack
    // ---------------------------------------------------------

    public static int recursive(int[] arr, int n) {

        // One element is its own maximum.
        if (n == 1) {
            return arr[0];
        }

        // Compare the last element with the maximum of the remaining elements.
        return Math.max(arr[n - 1], recursive(arr, n - 1));
    }
}