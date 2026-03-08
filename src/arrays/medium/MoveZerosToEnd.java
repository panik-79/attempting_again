package arrays.medium;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/move-zeros-to-end?subject=dsa
public class MoveZerosToEnd {

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

        moveZerosToEnd(arr);
        Utils.println("Output array: " + Arrays.toString(arr));
    }

    public static void moveZerosToEnd(int[] arr) {

        int zeroPtrIdx = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != 0) {
                Utils.swap(arr, i, zeroPtrIdx);
                zeroPtrIdx++;
            }

        }

    }

    public static void moveZerosToEndNaive(int[] arr) {

        // idx will track the position where the next non-zero element
        // should be placed
        int idx = 0;

        // First pass: move all non-zero elements to the front
        for (int i = 0; i < arr.length; i++) {

            // If current element is not zero
            if (arr[i] != 0) {

                // Place it at the next available position at the front
                arr[idx] = arr[i];

                // Move idx forward for the next non-zero element
                idx++;
            }
        }

        // Second pass: fill the remaining positions with zeros
        // starting from idx to the end of the array
        for (int i = idx; i < arr.length; i++) {
            arr[i] = 0;
        }
    }
}


/*
APPROACH: Two Pointer Partitioning

Goal:
Move all zeros to the end of the array while maintaining the relative
order of non-zero elements.

Idea:
We maintain a pointer `zeroPtrIdx` that always points to the position
where the next non-zero element should be placed.

Conceptually the array is divided into three regions:

[ processed non-zero elements | unprocessed elements | zeros ]

As we scan the array:

1. `i` scans the array from left to right.
2. `zeroPtrIdx` tracks where the next non-zero element should go.

Whenever we find a non-zero element:
- Swap it with the element at `zeroPtrIdx`.
- Move `zeroPtrIdx` forward.

This gradually pushes all non-zero elements to the front of the array
while zeros naturally shift toward the end.

Example:
Input:
[0, 1, 4, 0, 5, 2]

Steps:

i=1 → 1 is non-zero → swap with index 0
[1, 0, 4, 0, 5, 2]

i=2 → 4 is non-zero → swap with index 1
[1, 4, 0, 0, 5, 2]

i=4 → 5 is non-zero → swap with index 2
[1, 4, 5, 0, 0, 2]

i=5 → 2 is non-zero → swap with index 3
[1, 4, 5, 2, 0, 0]

Result:
[1, 4, 5, 2, 0, 0]

Time Complexity: O(n)
Space Complexity: O(1)

This pattern is commonly called a "two-pointer partition" technique
and appears in many array problems.
*/