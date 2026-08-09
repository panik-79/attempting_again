package recursion.basic;

import util.Utils;

public class IsArraySorted {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int n = scanner.nextInt("Enter arr size : ");
        int[] arr = scanner.nextIntArray(n, "Enter arr : ");
        boolean isArraySorted = isSorted(arr, n);
        Utils.println("isSorted : " + isArraySorted);

    }

    public static boolean isSorted (int[] arr, int n) {

        if (n == 0 || n == 1) {
            return true;
        }

        return (arr[n - 1] >= arr[n - 2]) &&
                isSorted(arr, n - 1);
    }

}
