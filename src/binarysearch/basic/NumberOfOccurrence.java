package binarysearch.basic;

import util.Utils;

import java.util.Arrays;

public class NumberOfOccurrence {

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

        int occurrences = findOccurrences(arr, key);
        Utils.println("Occurrences: " + occurrences);
    }

    public static int findOccurrences(int[] arr, int key) {

        if (LastOccurrence.findRightMostOccurrence(arr, key) == -1) {
            return 0;
        }

        return LastOccurrence.findRightMostOccurrence(arr, key) - FirstOccurrence.findLeftMostOccurrence(arr, key) + 1;
    }
}
