package arrays.medium;

import util.ArrayUtils;
import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/union-of-two-sorted-arrays?subject=dsa
public class UnionOfTwoSortedArrays {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Enter size of array1: ");
        int s1 = scanner.nextInt();
        int[] a1 = ArrayUtils.randomSortedIntArray(s1, 1, 20);

        Utils.print("Enter size of array2: ");
        int s2 = scanner.nextInt();
        int[] a2 = ArrayUtils.randomSortedIntArray(s2, 1, 20);

        // Edge case: if any array is empty
        if (s1 <= 0 || s2 <= 0) {
            Utils.println("Empty input array");
            return;
        }

        Utils.println("Input array1: " + Arrays.toString(a1));
        Utils.println("Input array2: " + Arrays.toString(a2));

        List<Integer> unionArr = union(a1, a2);
        Utils.println("Union array: " + unionArr);
    }

    /*
        APPROACH (Two Pointer Technique)

        Since arrays are sorted, we can traverse both arrays simultaneously.

        Steps:
        1. Use two pointers i and j for arrays a1 and a2.
        2. Compare elements:
            - If a1[i] < a2[j] → add a1[i]
            - If a1[i] > a2[j] → add a2[j]
            - If equal → add only once
        3. Move the pointer of the element we processed.
        4. Avoid duplicates by checking the last inserted element.
        5. After one array finishes, add remaining elements from the other array.

        Time Complexity:  O(n + m)
        Space Complexity: O(n + m)
     */
    public static List<Integer> union(int[] a1, int[] a2) {

        List<Integer> unionArr = new ArrayList<>();

        int i = 0;
        int j = 0;

        // Traverse both arrays
        while (i < a1.length && j < a2.length) {

            // Case 1: element from a1 is smaller
            if (a1[i] <= a2[j]) {

                // Add element only if it is not already added
                if (unionArr.isEmpty() || unionArr.get(unionArr.size() - 1) != a1[i]) {
                    unionArr.add(a1[i]);
                }

                i++;
            }

            // Case 2: element from a2 is smaller
            else {

                if (unionArr.isEmpty() || unionArr.get(unionArr.size() - 1) != a2[j]) {
                    unionArr.add(a2[j]);
                }

                j++;
            }
        }

        // Add remaining elements of array1
        while (i < a1.length) {

            if (unionArr.isEmpty() || unionArr.get(unionArr.size() - 1) != a1[i]) {
                unionArr.add(a1[i]);
            }

            i++;
        }

        // Add remaining elements of array2
        while (j < a2.length) {

            if (unionArr.isEmpty() || unionArr.get(unionArr.size() - 1) != a2[j]) {
                unionArr.add(a2[j]);
            }

            j++;
        }

        return unionArr;
    }
}