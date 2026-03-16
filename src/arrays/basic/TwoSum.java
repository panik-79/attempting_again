package arrays.basic;

import util.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://takeuforward.org/plus/dsa/problems/two-sum?subject=dsa
public class TwoSum {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        Utils.print("Target: ");
        int target = scanner.nextInt();
        Utils.println("Input array: " + Arrays.toString(arr));
        Utils.println("Target: " + target);

        Utils.Pair<Integer, Integer> solution = bruteTwoSum(arr, target);
        Utils.println(solution);

    }

    // TC: O(n^2), SC: O(1)
    private static Utils.Pair<Integer, Integer> bruteTwoSum (int[] arr, int target) {
        for  (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new Utils.Pair<>(i, j);
                }
            }
        }
        return null;
    }

    // TC: O(n)*O(1) -> lookup in map is O(1) , SC: O(n)
    private static Utils.Pair<Integer, Integer> betterTwoSum (int[] arr, int target) {

        Map<Integer, Integer> numToIdxMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (numToIdxMap.containsKey(target - arr[i])) {
                return new Utils.Pair<>(i, numToIdxMap.get(target - arr[i]));
            } else {
                numToIdxMap.put(arr[i], i);
            }
        }
        return null;
    }

    private static Utils.Pair<Integer, Integer> optimalTwoSum (int[] arr, int target) {
        // https://takeuforward.org/plus/dsa/problems/two-sum?subject=dsa&tab=editorial&approach=optimal
        return null;
    }

}
