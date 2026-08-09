package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

public class LastOccurrence {

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

        int rightOccIdx = findRightMostOccurrence(arr, key);
        Utils.println("Rightmost Occurrence Idx: " + rightOccIdx);

    }

    public static int findRightMostOccurrence (int[] arr, int key) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr-leftPtr)/2;
            int midValue = arr[midIdx];

            if (midValue <= key) {
                leftPtr = midIdx + 1;
            } else {
                rightPtr = midIdx - 1;
            }
        }

        if (rightPtr >= 0 && arr[rightPtr] == key) {
            return rightPtr;
        }

        return -1;
    }

}
