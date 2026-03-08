package arrays.basic;

import util.Utils;

import java.util.Arrays;

public class IsSorted {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        boolean isSorted = checkSorted(arr, size);

        Utils.print("Array: " + Arrays.toString(arr) + " isSorted: " + isSorted);
    }

    private static boolean checkSorted(int[] arr, int size) {

        for (int i = 1; i < size; i++) {
            if(arr[i-1] > arr[i]){
                return false;
            }
        }

        return true;
    }

}
