package arrays.hard;

import util.Utils;

// https://leetcode.com/problems/product-of-array-except-self/description/
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt("Enter Size: ");
        int[] arr = scanner.nextIntArray(size, "Enter integer array: ");

        Utils.printArray(brute(arr, size));
        Utils.printArray(better(arr, size));
        Utils.printArray(optimal(arr, size));
    }

    /**
     * Brute Force (Division)
     *
     * Time  : O(n)
     * Space : O(1)
     *
     * NOTE:
     * This solution does NOT work when the array contains zero(s).
     * LeetCode also explicitly disallows using division.
     */
    public static int[] brute(int[] arr, int size) {

        int product = 1;
        for (int num : arr) {
            product *= num;
        }

        int[] answer = new int[size];

        for (int i = 0; i < size; i++) {
            answer[i] = product / arr[i];
        }

        return answer;
    }

    /**
     * Better
     *
     * Build prefix and suffix product arrays.
     *
     * Time  : O(n)
     * Space : O(n)
     */
    public static int[] better(int[] arr, int size) {

        int[] prefix = new int[size];
        int[] suffix = new int[size];
        int[] answer = new int[size];

        prefix[0] = 1;
        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] * arr[i - 1];
        }

        suffix[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < size; i++) {
            answer[i] = prefix[i] * suffix[i];
        }

        return answer;
    }

    /**
     * Optimal
     *
     * Store prefix products directly inside the answer array,
     * then multiply by suffix products in a second pass.
     *
     * Time  : O(n)
     * Space : O(1) (excluding output array)
     */
    public static int[] optimal(int[] arr, int size) {

        int[] answer = new int[size];

        answer[0] = 1;
        for (int i = 1; i < size; i++) {
            answer[i] = answer[i - 1] * arr[i - 1];
        }

        int suffixProduct = 1;
        for (int i = size - 1; i >= 0; i--) {
            answer[i] *= suffixProduct;
            suffixProduct *= arr[i];
        }

        return answer;
    }
}