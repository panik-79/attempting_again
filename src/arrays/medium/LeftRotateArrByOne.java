package arrays.medium;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/left-rotate-array-by-one?subject=dsa
public class LeftRotateArrByOne {

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

        leftRotate(arr);
        Utils.println("Array after rotating: " + Arrays.toString(arr));

    }

    public static void leftRotate(int[] arr) {

        // save leftmost el
        int leftMost = arr[0];

        // shift
        for (int i = 1; i < arr.length; i++) {
            arr[i-1] = arr[i];
        }

        // replace last el by leftmost el
        arr[arr.length - 1] = leftMost;

    }

}

// NOTE :
// alternate solution : start swapping from l -> r
