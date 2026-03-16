package arrays.basic;

import util.ArrayUtils;
import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/leaders-in-an-array?subject=dsa
public class LeadersInAnArray {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));

        List<Integer> leaders = bruteFindLeaders(arr);
        Utils.println("Leaders: " + leaders);

    }

    public static List<Integer> bruteFindLeaders(int[] arr) {

        List<Integer> leaders = new ArrayList<>();

        for ( int i = 0; i < arr.length; i++) {

            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {

                // leader has to be strictly greater (therefore >=)
                if (arr[j] >= arr[i]) {
                    isLeader = false;
                    break;
                }
            }

            if (isLeader) {
                leaders.add(arr[i]);
            }
        }

        return leaders;
    }

    public static List<Integer> optimalFindLeaders (int[] arr) {

        List<Integer> leaders = new ArrayList<>();
        int largestInRight = arr[arr.length - 1];

        leaders.add(largestInRight);

        for (int i = arr.length - 2; i >= 0; i--) {

            if (arr[i] > largestInRight) {
                largestInRight = arr[i];
                leaders.add(largestInRight);
            }

        }

        ArrayUtils.reverseInPlace(ArrayUtils.toIntArray(leaders));
        return leaders;
    }


}
