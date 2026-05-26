package binarysearch;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/search-x-in-sorted-array?subject=dsa
public class BinarySearch {

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

        int targetIdx = binarySearch(arr, target);
        Utils.println("Target index: " + targetIdx);

    }

    public static int binarySearch (int[] arr, int target) {

        int size = arr.length;
        int leftPtr = 0;
        int rightPtr = size - 1;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr - leftPtr) / 2;
            int midValue = arr[midIdx];

            if (midValue == target) {
                return midIdx;
            }
            else if (target < midValue) {
                rightPtr = midIdx - 1;
            }
            else {
                leftPtr = midIdx + 1;
            }
        }

        return -1;
    }

}
