package binarysearch.medium;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/search-in-rotated-sorted-array-i?subject=dsa
public class SearchInSortedRotatedArrayI {

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

        int targetIdx = searchInSortedRotatedArr(arr, target);
        Utils.println("Target index: " + targetIdx);
    }

    public static int searchInSortedRotatedArr(int[] arr, int target) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr <= rightPtr) {

            int midIdx = ( leftPtr + rightPtr ) / 2;
            int midValue = arr[midIdx];

            if (midValue == target) {
                return midIdx;
            }
            else if (arr[leftPtr] <= midValue) {
                if (target >= arr[leftPtr] && target < midValue) {
                    rightPtr = midIdx - 1;
                } else {
                    leftPtr = midIdx + 1;
                }
            }
            else if (midValue <= arr[rightPtr]) {
                if (target > midValue && target <= arr[rightPtr]) {
                    leftPtr = midIdx + 1;
                } else {
                    rightPtr = midIdx - 1;
                }
            }
        }

        return -1;
    }

}


// Intuition:
// The optimal approach would be by dividing the array in halves and implement binary search.
// The most important thing to note here is that, at any middle point, one side of the array will still be sorted.
// Use this logic & by figuring out which half is sorted, decide which half to keep searching in,
// making the search efficient even in a rotated array.

// Approach:
// Start with two pointers: low at the beginning and high at the end of the array & calculate the midpoint (mid).
// If mid-value is the target, return midIndex.
// Determine which half of the array is sorted. If the left half is sorted and the target is within this range,
// search in the left half.
// Otherwise, search in the right half, if it is sorted and the target is within this range,
// search in the right half. Otherwise, search in the left half.
// Continue this process until the pointers low and high cross. If the target is not found, return -1.
