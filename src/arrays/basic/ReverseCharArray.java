package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://leetcode.com/problems/reverse-string/description/
public class ReverseCharArray {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();

        String s = scanner.next();
        char[] arr = s.toCharArray();
        Utils.println("Initial array: " + Arrays.toString(arr));
        reverse(arr);
        Utils.println("Reversed array: " + Arrays.toString(arr));

    }

    private static void reverse(char[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

}
