package arrays.brutal;

import util.Utils;

import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/find-the-duplicate-number/description/
public class FindDuplicateNumber {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));
        int duplicateNumber = findDuplicate(arr);
        Utils.print("Duplicate number: " + duplicateNumber);
    }

    public static int findDuplicate(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;

            if (arr[index] < 0) {
                return Math.abs(arr[i]);
            }

            arr[index] = -arr[index];
        }

        return -1;
    }

}
