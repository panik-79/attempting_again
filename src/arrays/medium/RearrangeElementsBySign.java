package arrays.medium;

import util.ArrayUtils;
import util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://takeuforward.org/plus/dsa/problems/rearrange-array-elements-by-sign?subject=dsa
public class RearrangeElementsBySign {

    public static void main(String[] args) {
        Utils.FastScanner scanner = new Utils.FastScanner();

        Utils.print("Size: ");
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        Utils.println("Input array: " + Arrays.toString(arr));

        List<Integer> rearrangedArray = optimalRearrange(arr);
        Utils.println("Rearranged array: " + rearrangedArray);
    }

    public static List<Integer> bruteRearrange(int[] arr) {

        List<Integer> rearrangedArray = new ArrayList<>();

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        for (int el : arr) {
            if (el > 0) {
                positives.add(el);
            } else {
                negatives.add(el);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                rearrangedArray.add(positives.get(i / 2));
            } else {
                rearrangedArray.add(negatives.get(i / 2));
            }
        }

        return rearrangedArray;

    }

    public static List<Integer> optimalRearrange(int[] arr) {

        List<Integer> rearrangedArray = ArrayUtils.getPrefilledArrayList(arr.length);

        int posIdx = 0, negIdx = 1;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {
                rearrangedArray.set(posIdx, arr[i]);
                posIdx += 2;
            } else {
                rearrangedArray.set(negIdx, arr[i]);
                negIdx += 2;
            }

        }

        return rearrangedArray;
    }

}
