package arrays.medium;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/remove-duplicates-from-sorted-array?subject=dsa
public class RemoveDuplicatesFromSortedArr {

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

        Utils.sortAsc(arr);
        Utils.println("Sorted array: " + Arrays.toString(arr));

        int uniqueElements = removeDuplicates(arr);

        Utils.println("Unique elements count: " + uniqueElements);
        Utils.println("Array after removing duplicates: "
                + Arrays.toString(Arrays.copyOf(arr, uniqueElements)));
    }

    public static int removeDuplicates(int[] arr) {

        /*
         TWO POINTER APPROACH

         idx -> slow pointer
                Points to the position where next unique element should go

         i   -> fast pointer
                Traverses the array to find unique elements
        */

        int idx = 1; // First element is always unique

        // Start checking from second element
        for (int i = 1; i < arr.length; i++) {

            /*
             If current element is different from previous element
             it means we found a new unique element
            */
            if (arr[i] != arr[i - 1]) {

                // Place the unique element at idx position
                arr[idx] = arr[i];

                // Move idx forward for next unique placement
                idx++;
            }
        }

        /*
         idx now represents the total number of unique elements.

         Example:
         Input  : [1,1,2,2,3]
         Output : [1,2,3,2,3]
                   ^
                   idx = 3

         First idx elements contain the unique values.
        */
        return idx;
    }

}
