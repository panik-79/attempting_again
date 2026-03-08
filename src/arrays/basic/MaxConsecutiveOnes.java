package arrays.basic;

import util.Utils;

import java.util.Arrays;

// https://takeuforward.org/plus/dsa/problems/maximum-consecutive-ones?subject=dsa
public class MaxConsecutiveOnes {

    public static void main(String[] args) {

        Utils.FastScanner scanner = new Utils.FastScanner();
        int size = scanner.nextInt();
        int[] arr = scanner.nextIntArray(size);

        if (size <= 0) {
            Utils.println("Empty input array");
            return;
        } else {
            Utils.println("Input array: " + Arrays.toString(arr));
        }

        int maxConsecutiveOnes = getMaxConsecutiveOnes(arr, size);
        Utils.println("Max consecutive ones: " + maxConsecutiveOnes);
    }

    public static int getMaxConsecutiveOnes(int[] arr, int size){

        int maxOnesCount = 0;
        int currentCount = 0;

        for (int i = 0; i < size ; i++){

            if(arr[i] == 1) {
                currentCount++;
                maxOnesCount = Math.max(maxOnesCount, currentCount);
            } else {
                currentCount = 0;
            }

        }

        return maxOnesCount;
    }

}

// NOTE:
// common mistake: update maxOnesCount on encountering zero
// result: if array ends in 1 and is part of longest "1" stream, maxOnesCount never gets updated
// e.g. : [1, 1, 0, 1, 0, 1, 1, 1, 1] -> expected output : 4, actual result : 2