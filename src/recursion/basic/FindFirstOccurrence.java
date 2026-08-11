package recursion.basic;

import util.Utils;

public class FindFirstOccurrence {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter arr size: ");
        int[] arr = scanner.nextIntArray(n, "Enter arr: ");
        int target = scanner.nextInt("Enter target: ");

        int index = recursive(arr, 0, target);

        Utils.println("First occurrence index: " + index);
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static int recursive(int[] arr, int index, int target) {

        // Target was not found after checking the entire array.
        if (index == arr.length) {
            return -1;
        }

        // Return immediately when the first occurrence is found.
        if (arr[index] == target) {
            return index;
        }

        // Move to the next element.
        return recursive(arr, index + 1, target);
    }
}