package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/lower-bound-?subject=dsa&approach=optimal
public class LowerBound {

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

        int lowerBoundIdx = findLowerBoundIdx(arr, key);
        Utils.println("Lower bound index: " + lowerBoundIdx);

    }

    public static int findLowerBoundIdx(int[] arr, int key) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;
        int potentialLowerBoundIdx = arr.length;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr-leftPtr)/2;
            int midValue = arr[midIdx];

            if (midValue >= key) {
                // look for same key in left part of the array
                rightPtr = midIdx - 1;
                potentialLowerBoundIdx = midIdx;
            }
            else {
                // look into right part of the array
                leftPtr = midIdx + 1;
            }
        }

        return potentialLowerBoundIdx;
    }

}
