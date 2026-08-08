package arrays.medium;

import util.Utils;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-consecutive-sequence/description/
// https://takeuforward.org/plus/dsa/problems/longest-consecutive-sequence-in-an-array
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt("Enter Size: ");
        int[] arr = scanner.nextIntArray(size, "Enter integer array: ");

        int longestSeqLength = getLengthOptimal(arr);
        Utils.println("Longest Seq length: " + longestSeqLength);
    }

    // O(max - min) time
    // O(max - min) space
    // Works well when the range between min and max is small.
    public static int getLengthUsingPresenceArray(int[] arr) {

        int min = Utils.min(arr);
        int max = Utils.max(arr);

        int[] seen = new int[max - min + 1];

        for (int j : arr) {
            seen[j - min] = 1;
        }

        int maxSeq = 0;
        int currMax = 0;

        for (int val : seen) {
            if (val == 1) {
                currMax++;
            } else {
                maxSeq = Math.max(maxSeq, currMax);
                currMax = 0;
            }
        }

        // Important: handles a sequence that ends at the last element.
        maxSeq = Math.max(maxSeq, currMax);

        return maxSeq;
    }


    // Better: Sorting
    // O(n log n) time
    // O(1) extra space if sorting in-place
    public static int getLengthBetter(int[] arr) {

        if (arr.length == 0) {
            return 0;
        }

        Utils.sortAsc(arr);

        int maxSeq = 1;
        int currSeq = 1;

        for (int i = 1; i < arr.length; i++) {

            // Duplicate: don't break the sequence.
            if (arr[i] == arr[i - 1]) {
                continue;
            }

            // Consecutive number.
            if (arr[i] == arr[i - 1] + 1) {
                currSeq++;
            }
            // Sequence broke.
            else {
                maxSeq = Math.max(maxSeq, currSeq);
                currSeq = 1;
            }
        }

        return Math.max(maxSeq, currSeq);
    }


    // Optimal: HashSet
    // O(n) average time
    // O(n) space
    public static int getLengthOptimal(int[] arr) {

        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        int maxSeq = 0;

        for (int num : set) {

            // Start counting only if this is
            // the beginning of a sequence.
            if (!set.contains(num - 1)) {

                int currNum = num;
                int currSeq = 1;

                while (set.contains(currNum + 1)) {
                    currNum++;
                    currSeq++;
                }

                maxSeq = Math.max(maxSeq, currSeq);
            }
        }

        return maxSeq;
    }
}