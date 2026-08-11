package recursion.basic;

import util.Utils;

public class SumOfArray {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        int n = scanner.nextInt("Enter arr size: ");
        int[] arr = scanner.nextIntArray(n, "Enter arr: ");

        int sum = recursive(arr, n);

        Utils.println("Sum: " + sum);
    }

    // Time  : O(n)
    // Space : O(n) due to recursion stack
    public static int recursive(int[] arr, int n) {

        if (n == 1) return arr[0];

        return arr[n-1] + recursive(arr, n-1);
    }
}