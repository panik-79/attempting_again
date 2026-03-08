package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/second-largest-element?subject=dsa
public class SecondLargestElement {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        if (size <= 2) {
            Utils.println("Invalid input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        Utils.Pair<Integer, Integer> pair = getLargestElements(arr, size);
        Utils.println("Largest: " + pair.first + ", Second Largest: " + pair.second);
    }

    public static Utils.Pair<Integer, Integer> getLargestElements(int[] arr, int size) {

        int largest = arr[0];
        int secondLargest = Integer.MIN_VALUE;
        boolean foundSecond = false;

        for (int i = 1; i < size; i++) {

            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
                foundSecond = true;
            }
            else if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
                foundSecond = true;
            }
        }

        if (!foundSecond) {
            Utils.println("No second largest element");
        }

        return new Utils.Pair<>(largest, secondLargest);
    }

}
