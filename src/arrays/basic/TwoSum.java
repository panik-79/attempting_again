package arrays.basic;

import util.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum/description/
public class TwoSum {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);
        Utils.print("Target: ");
        int target = scanner.nextInt();

        Utils.println("Input array: " + Arrays.toString(arr));
        int[] ans = twoSum(arr, target);
        Utils.println("Answer: " + Arrays.toString(ans));
    }

    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            int diff = target - curr;

            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }

            map.put(curr, i);
        }

        return new int[]{};
    }
}
