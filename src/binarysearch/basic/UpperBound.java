package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/upper-bound?subject=dsa&approach=optimal
public class UpperBound {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int key = scanner.nextInt();

        if (size <= 0) {
            Utils.println("Empty input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        int lowerBoundIdx = findUpperBoundIdx(arr, key);
        Utils.println("Upper bound index: " + lowerBoundIdx);

    }

    public static int findUpperBoundIdx(int[] arr, int key) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;
        int potentialUpperBoundIdx = arr.length;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr-leftPtr)/2;
            int midValue = arr[midIdx];

            if (midValue <= key) {
                // look in the right part of the array
                leftPtr = midIdx + 1;
                potentialUpperBoundIdx = midIdx;
            }
            else {
                // look into left part of the array
                rightPtr = midIdx - 1;
            }
        }

        return potentialUpperBoundIdx;
    }

}
