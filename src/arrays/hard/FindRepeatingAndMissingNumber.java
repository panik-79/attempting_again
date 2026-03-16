package arrays.hard;

import util.Utils;

import java.util.Arrays;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/find-the-repeating-and-missing-number?subject=dsa
public class FindRepeatingAndMissingNumber {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));
        Utils.Pair<Integer, Integer> result = findRepeatingAndMissingNumber(arr);
        Utils.println("Repeating number: " + result.first);
        Utils.println("Missing number: " + result.second);

    }

    public static Utils.Pair<Integer, Integer> findRepeatingAndMissingNumber (int[] arr) {

    }

}