package recursion.basic;

import util.Utils;

// https://leetcode.com/problems/binary-search/description/
public class BinarySearch {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt("Size : ");
        int[] arr = scanner.nextIntArray(n, "Enter array elements: ");
        int key = scanner.nextInt("Key: ");
        int low = 0;
        int high = arr.length - 1;
        int index = search(arr, low, high, key);
        Utils.println("Idx: " + index);

    }

    // Time Complexity: O(log n)
    // Space Complexity: O(log n) - recursive call stack
    public static int search(int[] arr, int low, int high, int key) {

        // Base condition
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        // Found
        if (arr[mid] == key) {
            return mid;
        }
        // Search left half
        else if (arr[mid] > key) {
            return search(arr, low, mid - 1, key);
        }
        // Search right half
        else {
            return search(arr, mid + 1, high, key);
        }
    }

}
