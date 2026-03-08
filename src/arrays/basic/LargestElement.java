package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/largest-element?subject=dsa
public class LargestElement {

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

        int largestEl = getLargestElement(arr, size);
    }

    public static int getLargestElement(int[] arr, int size){

        int idx = 0;
        int largestEl = Integer.MIN_VALUE;
        for(int i = 1; i < size; i++){
            if(arr[i] > largestEl) {
                largestEl = arr[i];
                idx = i;
            }
        }

        Utils.println("Largest element : " + largestEl + " found at idx: " + idx);
        return largestEl;
    }

}
