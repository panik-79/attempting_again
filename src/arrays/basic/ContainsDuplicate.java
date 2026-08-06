package arrays.basic;


import util.Utils;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/contains-duplicate/description/
public class ContainsDuplicate {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));
        boolean containsDuplicate = containsDuplicate(arr);
        Utils.println("Contains Duplicate: " + containsDuplicate);
    }

    public static boolean containsDuplicate(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}
