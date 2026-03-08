package arrays.basic;

import util.Utils;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Initial array: " + Arrays.toString(arr));
        reverse(arr, size);
        Utils.println("Reversed array: " + Arrays.toString(arr));

    }

    private static void reverse(int[] arr, int size) {
        int left = 0;
        int right = size - 1;

        while (left < right) {
            Utils.swap(arr, left, right);
            left++;
            right--;
        }
    }

}
