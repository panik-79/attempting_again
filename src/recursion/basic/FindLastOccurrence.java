package recursion.basic;

import util.Utils;

public class FindLastOccurrence {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter arr size: ");
        int[] arr = scanner.nextIntArray(n, "Enter arr: ");
        int target = scanner.nextInt("Enter target: ");

        int index = recursive(arr, n - 1, target);

        Utils.println("Last occurrence index: " + index);
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static int recursive(int[] arr, int index, int target) {

        // Target was not found after checking the entire array.
        if (index == -1) {
            return -1;
        }

        // Starting from the end makes the first match the last occurrence.
        if (arr[index] == target) {
            return index;
        }

        // Move towards the beginning of the array.
        return recursive(arr, index - 1, target);
    }
}