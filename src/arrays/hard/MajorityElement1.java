package arrays.hard;

import util.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://takeuforward.org/plus/dsa/problems/majority-element-i?subject=dsa
public class MajorityElement1 {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));
        int majorityEl = optimalFindMajorityElement(arr);
        Utils.println("Majority element: " + majorityEl);
    }

    // TC: O(n^2), SC: O(1)
    public static int bruteFindMajorityElement (int[] arr) {

        int n = arr.length;

        // Iterate through each element of the array
        for (int i = 0; i < n; i++) {

            // Counter to count occurrences of arr[i]
            int cnt = 0;

            // Count the frequency of arr[i] in the array
            for (int j = 0; j < n; j++) {
                if (arr[j] == arr[i]) {
                    cnt++;
                }
            }

            // Check if frequency of arr[i] is greater than n/2
            if (cnt > (n / 2)) {
                // Return the majority element
                return arr[i];
            }
        }

        // Return -1 if no majority element is found
        return -1;
    }


    // Hashing approach - TC: O(n), SC: O(n) (due to hashmap)
    public static int betterFindMajorityElement(int[] arr) {

        int n = arr.length;

        // Map to store frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequency of every number in the array
        for (int num : arr) {
            // getOrDefault returns the current count if present,
            // otherwise returns 0, then we increment it by 1
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Traverse the map to find the element whose frequency is greater than n/2
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {

            int element = entry.getKey();      // the number
            int count = entry.getValue();      // its frequency

            // Check majority condition
            if (count > n / 2) {
                return element;
            }
        }

        // If no majority element exists
        return -1;
    }

    // Moore's Voting Algorithm - Time Complexity: O(n), Space Complexity: O(1)
    public static int optimalFindMajorityElement(int[] arr) {

        int n = arr.length;
        // potential majority element
        int candidate = getCandidate(arr);

        // Verify if candidate is actually majority
        int candidateCount = 0;
        for (int el : arr) {
            if (el == candidate) {
                candidateCount++; // count occurrences of candidate
            }
        }

        // Check if candidate occurs more than n/2 times
        if (candidateCount > n / 2) {
            return candidate;
        }

        // no majority element exists
        return -1; 
    }

    private static int getCandidate(int[] arr) {
        int candidate = arr[0];
        int count = 0;          // count for candidate

        // Find a potential candidate
        for (int el : arr) {
            if (count == 0) {
                count = 1;      // reset count and choose new candidate
                candidate = el;
            } else if (el == candidate) {
                count++;        // increment count for same element
            } else {
                count--;        // decrement count for different element
            }
        }
        return candidate;
    }

}