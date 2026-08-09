package binarysearch.medium;

import util.Utils;

import java.util.Arrays;

public class SearchInSortedRotatedArrayII {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int target = scanner.nextInt();

        if (size <= 0) {
            Utils.println("Empty input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        boolean exists = searchInSortedRotatedArr(arr, target);
        Utils.println("Exists: " + exists);
    }

    public static boolean searchInSortedRotatedArr(int[] arr, int target) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        /*
         * IDEA:
         * Find which half is sorted.
         * Then check whether target lies inside that half.
         *
         * DUPLICATE ISSUE:
         * If arr[left] == arr[mid] == arr[right],
         * we cannot determine the sorted half.
         * So shrink boundaries.
         */

        while (leftPtr <= rightPtr) {

            int midIdx = leftPtr + (rightPtr - leftPtr) / 2;
            int midValue = arr[midIdx];

            // Target found
            if (midValue == target) {
                return true;
            }

            // Duplicate ambiguity
            if (arr[leftPtr] == midValue && midValue == arr[rightPtr]) {
                leftPtr++;
                rightPtr--;
                continue;
            }

            // Left half sorted
            if (arr[leftPtr] <= midValue) {

                // Target inside left half
                if (target >= arr[leftPtr] && target < midValue) {
                    rightPtr = midIdx - 1;
                }

                // Search right half
                else {
                    leftPtr = midIdx + 1;
                }
            }

            // Right half sorted
            else {

                // Target inside right half
                if (target > midValue && target <= arr[rightPtr]) {
                    leftPtr = midIdx + 1;
                }

                // Search left half
                else {
                    rightPtr = midIdx - 1;
                }
            }
        }

        return false;
    }
}

/*
 * INTUITION:
 *
 * In rotated sorted arrays with distinct elements:
 * -> At least one half is always sorted.
 *
 * Normal rotated binary search works because
 * we can identify the sorted half.
 *
 * Duplicates break this logic.
 *
 * Example:
 * [1,1,1,1,0,1]
 *
 * arr[left] == arr[mid] == arr[right]
 *
 * We cannot identify:
 * -> left sorted?
 * -> right sorted?
 *
 * So remove duplicates from boundaries
 * until sorted-half detection becomes possible again.
 *
 * Time Complexity:
 * Average -> O(log n)
 * Worst   -> O(n) because of duplicates
 */
