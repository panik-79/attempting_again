package arrays.hard;

import util.Utils;

// https://leetcode.com/problems/product-of-array-except-self/description/
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt("Enter Size: ");
        int[] arr = scanner.nextIntArray(size, "Enter integer array: ");

        int[] answer = productExceptSelf(arr, size);
        Utils.printArray(answer);
    }

    public static int[] productExceptSelf(int[] arr, int size) {

        int[] answer = new int[size];

        // answer[i] stores the product of all elements to the left of i
        answer[0] = 1;
        for (int i = 1; i < size; i++) {
            answer[i] = answer[i - 1] * arr[i - 1];
        }

        // Multiply by the product of all elements to the right of i
        int suffixProduct = 1;
        for (int i = size - 1; i >= 0; i--) {
            answer[i] *= suffixProduct;
            suffixProduct *= arr[i];
        }

        return answer;
    }

}
