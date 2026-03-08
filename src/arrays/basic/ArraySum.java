package arrays.basic;

import util.Utils;

public class ArraySum {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }

        Utils.println(sum);
    }
}