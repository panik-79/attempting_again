package arrays.medium;

import util.ArrayUtils;
import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/intersection-of-two-sorted-arrays?subject=dsa
public class IntersectionOfTwoSortedArrays {

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

        List<Integer> intersectionArray = intersection(a1, a2);
        Utils.println("Intersection array: " + intersectionArray);
    }

    public static List<Integer> intersection (int[] a1, int[] a2) {

        List<Integer> intersectionArr = new ArrayList<>();

        int i = 0, j = 0;

        // iterate simultaneously
        while (i < a1.length && j < a2.length) {

            // if matches -> add (allows duplicates in intersection)
            if (a1[i] == a2[j]) {
                intersectionArr.add(a1[i]);
                i++;
                j++;
            }
            // move ptr to smaller el, attempting next idx el match
            else if (a1[i] < a2[j]) {
                i++;
            }
            else {
                j++;
            }

        }

        return intersectionArr;
    }

}
