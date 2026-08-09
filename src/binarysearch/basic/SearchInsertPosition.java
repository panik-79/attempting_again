package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/search-insert-position?subject=dsa
public class SearchInsertPosition {

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

        int insertionIdx = getInsertionIdx(arr, target);
        Utils.println("Insertion Idx: " + insertionIdx);
    }

    public static int getInsertionIdx(int[] arr, int target) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr <= rightPtr) {

            int midIdx = leftPtr + (rightPtr - leftPtr)/2;
            int midVal = arr[midIdx];

            if (midVal == target) {
                return midIdx;
            }
            else if (midVal < target) {
                leftPtr = midIdx + 1;
            }
            else {
                rightPtr = midIdx - 1;
            }
        }

        return leftPtr;
    }

}
