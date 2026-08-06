package arrays.basic;

import util.ArrayUtils;
import util.Utils;

import java.util.Arrays;

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
public class TwoSumOnSortedArray {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int target = scanner.nextInt();

        Utils.println("Input array: " + Arrays.toString(arr));
        ArrayUtils.sortAsc(arr);
        int[] ans = twoSumOnSortedArray(arr, target);
        Utils.println("Answer: " + Arrays.toString(ans));
    }

    public static int[] twoSumOnSortedArray(int[] arr, int target) {
        int leftPtr = 0;
        int rightPtr = arr.length - 1;

        while (leftPtr < rightPtr) {
            int sum = arr[leftPtr] + arr[rightPtr];

            // Pair found
            if (sum == target) {
                return new int[]{leftPtr + 1, rightPtr + 1};
            }

            // Increase the sum by moving the left pointer right
            if (sum < target) {
                leftPtr++;
            }
            // Decrease the sum by moving the right pointer left
            else {
                rightPtr--;
            }
        }

        // No valid pair found
        return new int[]{};
    }
}
