package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/find-missing-number?subject=dsa
public class FindMissingNumber {

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

        long missingNum = findMissingNumber(arr);
        Utils.println("Missing number was: " + missingNum);
    }

    public static long findMissingNumber(int[] arr) {

        int n = arr.length;
        long expectedSum = ((long) n *(n+1)) / 2;
        long arraySum = Utils.sum(arr);
        return expectedSum - arraySum;

    }

}
