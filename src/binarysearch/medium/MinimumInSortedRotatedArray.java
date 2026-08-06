package binarysearch.medium;

import util.Utils;

import java.util.Arrays;

public class MinimumInSortedRotatedArray {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        if (size <= 0) {
            Utils.println("Empty input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        int min = findMin(arr);
        Utils.println("Min : " + min);
    }

    public static int findMin (int[] arr) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr <= rightPtr) {

            int midIdx = ( leftPtr + rightPtr ) / 2;
            int midValue = arr[midIdx];

            if (arr[leftPtr] <= midValue && midValue <= arr[rightPtr]) {
                return arr[leftPtr];
            }

            if (Math.abs(rightPtr - leftPtr) == 1) {
                return Math.min(arr[leftPtr], arr[rightPtr]);
            }

            // left part sorted
            if (arr[leftPtr] <= midValue) {
                leftPtr = midIdx + 1;
            }
            // right part sorted
            else if (midValue <= arr[rightPtr]) {
                rightPtr = midIdx - 1;
            }
        }

        return arr[rightPtr];
    }

}
