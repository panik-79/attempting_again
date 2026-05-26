package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/floor-and-ceil-in-sorted-array?subject=dsa
public class FloorAndCeil {

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

        int floor = getFloor(arr, key);
        int ceil = getCeil(arr, key);
        Utils.println(String.format("Floor- %d, Ceil- %d", floor, ceil));
    }

    public static int getFloor(int[] arr, int key) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        int potentialFloor = -1;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr - leftPtr) / 2;
            int midValue = arr[midIdx];

            if (midValue <= key) {
                // look into right part of the array
                leftPtr = midIdx + 1;

                // store potential floor value
                potentialFloor = arr[midIdx];
            }
            else {
                // look in left part of the array
                rightPtr = midIdx - 1;
            }
        }

        return potentialFloor;
    }

    public static int getCeil(int[] arr, int key) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        int potentialCeil = -1;

        while (leftPtr <= rightPtr) {
            int midIdx = leftPtr + (rightPtr - leftPtr) / 2;
            int midValue = arr[midIdx];

            if (midValue >= key) {
                // look in left part of the array
                rightPtr = midIdx - 1;

                // store potential floor value
                potentialCeil = arr[midIdx];
            }
            else {
                // look into right part of the array
                leftPtr = midIdx + 1;
            }
        }

        return potentialCeil;
    }

}
