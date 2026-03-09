package arrays.hard;

import util.ArrayUtils;
import util.Utils;

import java.util.*;

// https://takeuforward.org/plus/dsa/problems/majority-element-ii?subject=dsa
// ref. : https://www.youtube.com/watch?v=Q6L5SoS-fTo
public class MajorityElement2 {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));
        List<Integer> majorityEl = bruteFindMajorityElements(arr);
        Utils.println("Majority element: " + majorityEl);

    }

    // TC: O(n^2)
    public static List<Integer> bruteFindMajorityElements (int[] arr) {

        List<Integer> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {

            int count = ArrayUtils.count(arr, arr[i]);
            if (count > n/3 && !result.contains(arr[i])) {
                result.add(arr[i]);
            }

        }

        return result;
    }

    // Hashing - TC: O(n), SC: O(n)
    public static List<Integer> betterFindMajorityElements (int[] arr) {

        int n = arr.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int num : arr) {
            // getOrDefault returns the current count if present,
            // otherwise returns 0, then we increment it by 1
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Traverse the map to find the element whose frequency is greater than n/3
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

            int element = entry.getKey();      // the number
            int count = entry.getValue();      // its frequency

            // Check majority condition
            if (count > n / 3) {
                result.add((element));
            }
        }

        return result;
    }

    // Moore's voting algo - TC: O(n), SC: O(1)
    public static List<Integer> optimalFindMajorityElements (int[] arr) {

        List<Integer> result = new ArrayList<>();

        int n = arr.length;
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        for (int i = 0; i < n; i++) {
            if (Objects.equals(arr[i], candidate1)) {
                count1++;
            } else if (Objects.equals(arr[i], candidate2)) {
                count2++;
            } else if (count1 == 0 && !Objects.equals(arr[i], candidate2)) {
                candidate1 = arr[i];
                count1 = 1;
            } else if (count2 == 0 && !Objects.equals(arr[i], candidate1)) {
                candidate2 = arr[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // verify
        if (candidate1 != null && ArrayUtils.count(arr, candidate1) > n/3) result.add(candidate1);
        if (candidate2 != null && ArrayUtils.count(arr, candidate2) > n/3) result.add(candidate2);

        return result;
    }

}

/*
 * NOTE:
 * For the "majority element > n/3" problem, there can be AT MOST 2 elements in the final answer.
 * Reason:
 *   - Suppose 3 elements each appear more than n/3 times.
 *   - Then their total count > n/3 + n/3 + n/3 = n, which is impossible since array has only n elements.
 *   - Therefore, at most 2 elements can appear more than n/3 times.
 *
 * Generalization:
 *   - For finding elements appearing more than n/k times, there can be **at most k-1 elements** in the final answer.
 *   - The algorithm keeps k-1 candidate slots for potential majority elements and then verifies their counts.
 */
