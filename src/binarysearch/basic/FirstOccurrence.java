package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

public class FirstOccurrence {

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

        int leftOccIdx = findLeftMostOccurrence(arr, key);
        Utils.println("Leftmost Occurrence Idx: " + leftOccIdx);

    }

    public static int findLeftMostOccurrence (int[] arr, int key) {

        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr-leftPtr)/2;
            int midValue = arr[midIdx];

            if (midValue >= key) {
                rightPtr = midIdx - 1;
            } else {
                leftPtr = midIdx + 1;
            }
        }

        if (leftPtr < arr.length && arr[leftPtr] == key) {
            return leftPtr;
        }

        return -1;
    }

}
