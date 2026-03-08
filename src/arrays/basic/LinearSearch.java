package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/linear-search?subject=dsa
public class LinearSearch {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        int key = scanner.nextInt();

        Utils.println("Array: " + Arrays.toString(arr) + ", Key : " + key);
        int idx = find(arr, key);
        Utils.println("Index after lookup: " + idx);


    }

    private static int find(int[] arr, int key) {
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }

}
